package uima.analysisEngine;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import uima.types.InputData;
import uima.types.Lingpipedata;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.util.AbstractExternalizable;
/**
 * Using the lingpipe tool to analysis the data and pick the name of gene and the position informations.
 * @author root
 *
 */
public class LingpipeAnalysisEngine extends JCasAnnotator_ImplBase {

  static File modelFile = null;

  static final String PARAM_MODEL_FILE = "modelFile";

  Chunker chunker = null;

  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    String modelFilePath = (String) aContext.getConfigParameterValue(PARAM_MODEL_FILE);

    File modelFile = new File(modelFilePath);
    try {
      chunker = (Chunker) AbstractExternalizable.readObject(modelFile);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    super.initialize(aContext);
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    String key = null;
    FSIterator<Annotation> iterator = aJCas.getAnnotationIndex(InputData.type).iterator();
    if (iterator.hasNext()) {
      InputData data = (InputData) iterator.next();
      key = data.getKey();
    }

    FSIterator<Annotation> it = aJCas.getAnnotationIndex(InputData.type).iterator();

    String documentText = null;
    if (it.hasNext()) {
      InputData obj = (InputData) it.get();
      documentText = obj.getContent();
    }

    Iterator<Chunk> iter = chunker.chunk(documentText).chunkSet().iterator();
    while (iter.hasNext()) {
      Chunk chunk = iter.next();
      int start = chunk.start();
      int end = chunk.end();
      String phrase = documentText.substring(start, end);

      Lingpipedata lingpipe = new Lingpipedata(aJCas);
      lingpipe.setGene(phrase);
      lingpipe.setBegin(start);
      lingpipe.setEnd(end);
      lingpipe.addToIndexes();

    }

  }
}
