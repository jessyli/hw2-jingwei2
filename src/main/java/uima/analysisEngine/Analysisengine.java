package uima.analysisEngine;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import uima.types.InputData;

/**
 * split the key and the content of the input data
 * 
 * @author root
 *
 */
public class Analysisengine extends JCasAnnotator_ImplBase {

  public void process(JCas aJCas) throws AnalysisEngineProcessException {

    String documentText = aJCas.getDocumentText();
    String[] split = documentText.split(" +", 2);
    String key;
    String content;
    key = split[0];
    content = split[1];
    InputData rowann = new InputData(aJCas);
    rowann.setKey(key);
    rowann.setContent(content);
    rowann.addToIndexes(aJCas);
  }

}
