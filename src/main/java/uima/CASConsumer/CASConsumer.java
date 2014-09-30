package uima.CASConsumer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

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
 * write the result in JCAS 
 * @author root
 *
 */
public class CASConsumer extends CasConsumer_ImplBase {


  static final String PARAM_MODEL_FILE = "f";

  PrintWriter outer = null;

  @Override
  public void initialize() throws ResourceInitializationException {
    String modelFilePath = (String) getConfigParameterValue(PARAM_MODEL_FILE);
    try {
      File f = new File(modelFilePath);
      outer = new PrintWriter(new FileWriter(f));
    } catch (Exception e) {

      e.printStackTrace();
    }
    super.initialize();
  }

  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    
    String key = null;
    FSIterator<Annotation> iterator = jcas.getAnnotationIndex(InputData.type).iterator();
    if(iterator.hasNext()) {
      InputData data = (InputData) iterator.next();
      key = data.getKey();
    }
    
    FSIterator<Annotation> iter1 = jcas.getAnnotationIndex(uima.types.OutputGene.type).iterator();
   
    String geneName = "";
    int begin = -1;
    int end = -1;

    while (iter1.hasNext()) {
      OutputGene annotation = (OutputGene) iter1.next();
      geneName = annotation.getContent();
      System.out.println("[casConsumer]" + geneName);
      begin = annotation.getBegin();
      end = annotation.getEnd();
      outer.println(key + "|" + begin + " " + end + "|" + geneName);
    }
    
  }

  public void destroy() {
    super.destroy();
    outer.close();
  }

}
