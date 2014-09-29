package uima.analysisEngine;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import uima.types.Abnerdata;
import uima.types.InputData;
import abner.Tagger;
/**
 * using the Abner tool to get the gene name and position informations. Using the protein and DNA data as output data.
 *  * @author root
 *
 */
public class ABNERAnalysisengine extends JCasAnnotator_ImplBase {
  static Tagger tagger = null;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {

    if (null == tagger) {
      tagger = new Tagger();
      tagger.setTokenization(false);
    }

    super.initialize(aContext);
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {

    FSIterator<Annotation> it = aJCas.getAnnotationIndex(InputData.type).iterator();

    String documentText = null;
    if (it.hasNext()) {
      InputData obj = (InputData) it.get();
      documentText = obj.getContent();
    }

    String[][] entities = tagger.getEntities(documentText);
    if (entities.length > 0) {

      int overallStart = 0;
      for (int i = 0; i < entities[0].length; i++) {
        String gene = entities[0][i];
        String geneType = entities[1][i];

        if (geneType.equalsIgnoreCase("protein") //
                || geneType.equalsIgnoreCase("dna")) {

          int begin;
          int end;

          begin = documentText.indexOf(gene, overallStart);
          end = begin + gene.length();
          overallStart = end;

          Abnerdata abnerann = new Abnerdata(aJCas);
          abnerann.setGene(gene);
          abnerann.setBegin(begin);
          abnerann.setEnd(end);
          abnerann.addToIndexes();

        }
      }

    }

  }
}
