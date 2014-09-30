package uima.CASConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import uima.types.InputData;
import uima.types.OutputGene;

/**
 * evaluate the output data with the sample out data to get the precision, recall and F score of the
 * output data
 * 
 * @author root
 *
 */
public class Evaluation extends CasConsumer_ImplBase {
  public static final String PARAM_ANSWER_PATH = "answer_path";

  static Map<String, Set<String>> answer = null;

  private int falseHit;

  private int hit;

  private int missHit;

  @Override
  public void initialize() throws ResourceInitializationException {
    super.initialize();
    Object configParameterValue = getConfigParameterValue(PARAM_ANSWER_PATH);
    if (null == configParameterValue) {
      return;
    }
    answer = new HashMap<String, Set<String>>();

    String answerPath = (String) configParameterValue;
    // URL resource = Evaluation.class.getClassLoader().getResource(answerPath);
    InputStream resourceAsStream = Evaluation.class.getClassLoader()
            .getResourceAsStream(answerPath);
    BufferedReader reader = null;
    try {
      // AbstractExternalizable.readResourceObject(arg0)

      reader = new BufferedReader(new InputStreamReader(resourceAsStream, "utf-8"));
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    String line = null;

    try {
      while ((line = reader.readLine()) != null) {
        String[] split = line.split("\\|");
        if (split.length != 3) {
          System.err.println(line);
          return;
        }
        if (!answer.containsKey(split[0]))
          answer.put(split[0], new HashSet<String>());
        answer.get(split[0]).add(split[2]);

      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas = null;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    System.out.println("[Evaluation][processCas][test output]");
    String key = null;
    FSIterator<Annotation> iterator = jcas.getAnnotationIndex(InputData.type).iterator();
    if (iterator.hasNext()) {
      InputData data = (InputData) iterator.next();
      key = data.getKey();
    }

    FSIterator<Annotation> iter1 = jcas.getAnnotationIndex(uima.types.OutputGene.type).iterator();

    Set<String> answerSet = answer.get(key);

    if (null != answerSet) {
      int currentHit = 0;
      while (iter1.hasNext()) {
        OutputGene out = (OutputGene) iter1.next();
        if (answerSet.contains(out.getContent())) {
          currentHit++;
        } else {
          falseHit++;
        }
      }
      hit += currentHit;
      missHit += (answerSet.size() - currentHit);
    }
  }

  @Override
  public void destroy() {
    super.destroy();
    printBenchmark(hit, falseHit, missHit);
  }

  /**
   * 
   * @param hit
   * @param falseHit
   * @param missHit
   */

  private static void printBenchmark(int hit, int falseHit, int missHit) {
    double precision = (double) hit / (hit + falseHit);
    double recall = (double) hit / (hit + missHit);
    double f_score = 2 * precision * recall / (precision + recall);
    System.out.println(hit + "\t" + falseHit + "\t" + missHit);
    System.out.println(precision + "\t" + recall + "\t" + f_score);
  }

}