
/* First created by JCasGen Sat Sep 27 22:15:01 EDT 2014 */
package uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Sep 29 06:04:23 EDT 2014
 * @generated */
public class Lingpipedata_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Lingpipedata_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Lingpipedata_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Lingpipedata(addr, Lingpipedata_Type.this);
  			   Lingpipedata_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Lingpipedata(addr, Lingpipedata_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Lingpipedata.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("uima.types.Lingpipedata");
 
  /** @generated */
  final Feature casFeat_gene;
  /** @generated */
  final int     casFeatCode_gene;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getGene(int addr) {
        if (featOkTst && casFeat_gene == null)
      jcas.throwFeatMissing("gene", "uima.types.Lingpipedata");
    return ll_cas.ll_getStringValue(addr, casFeatCode_gene);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGene(int addr, String v) {
        if (featOkTst && casFeat_gene == null)
      jcas.throwFeatMissing("gene", "uima.types.Lingpipedata");
    ll_cas.ll_setStringValue(addr, casFeatCode_gene, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Lingpipedata_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_gene = jcas.getRequiredFeatureDE(casType, "gene", "uima.cas.String", featOkTst);
    casFeatCode_gene  = (null == casFeat_gene) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_gene).getCode();

  }
}



    