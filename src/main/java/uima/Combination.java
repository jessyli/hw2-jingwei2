package uima;

import java.util.ArrayList;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import uima.types.Abnerdata;
import uima.types.InputData;
import uima.types.Lingpipedata;
import uima.types.OutputGene;
/**
 * combine the data of lingpipe and Abner by finding the overlap of their data and pick one of them in the output data
 * @author root
 *
 */
public class Combination extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {

    String key = null;
    FSIterator<Annotation> iterator = jcas.getAnnotationIndex(InputData.type).iterator();
    if (iterator.hasNext()) {
      InputData data = (InputData) iterator.next();
      key = data.getKey();
    }

    FSIterator<Annotation> iter1 = jcas.getAnnotationIndex(uima.types.Abnerdata.type).iterator();
    FSIterator<Annotation> iter2 = jcas.getAnnotationIndex(uima.types.Lingpipedata.type).iterator();

    String[] split = jcas.getDocumentText().split(" +", 2);

    String content = split[1];
    char[] contentCharArray = content.toCharArray();
    int[] frequentArray = new int[contentCharArray.length];

    ArrayList<Abnerdata> abnerDataList = new ArrayList<Abnerdata>();
    ArrayList<Lingpipedata> lingpipeDataList = new ArrayList<Lingpipedata>();

    while (iter1.hasNext()) {
      abnerDataList.add((Abnerdata) iter1.next());
    }

    while (iter2.hasNext()) {
      lingpipeDataList.add((Lingpipedata) iter2.next());
    }

    for (Abnerdata ad : abnerDataList) {
      for (int i = ad.getBegin(); i < ad.getEnd(); i++) {
        frequentArray[i]++;
      }
    }
    for (Lingpipedata ld : lingpipeDataList) {
      for (int i = ld.getBegin(); i < ld.getEnd(); i++) {
        frequentArray[i]++;
      }
    }

    int previous_order = 0;
    int current = 0;
    int next = 0;
    int start = 0;
    for (int i = 0; i < frequentArray.length; i++) {
      next = frequentArray[i];
      if (current < next) {
        previous_order = 1;
        start = i;
      } else if (current > next) {
        if (previous_order != -1) {
          _annotateOutputGene(jcas, start, i, content.substring(start, i));
          previous_order = -1;
        }
      }
      current = next;

    }


  }

  public void destroy() {
    super.destroy();
  }

  private void _annotateOutputGene(JCas aJCas, int start, int end, String gene) {
    OutputGene obj = new OutputGene(aJCas);
    obj.setBegin(start);
    obj.setEnd(end);
    obj.setContent(gene);
    obj.addToIndexes();

    System.out.println("[Combine][add annotation]" + start + "\t" + end + "\t" + gene);
  }

}
