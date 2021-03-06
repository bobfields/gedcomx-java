/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.conclusion;

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.records.HasFieldBasedEvidence;
import org.gedcomx.rt.GedcomxModelVisitor;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * A form of a name.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "NameForm", propOrder = { "fullText", "parts", "fieldValueReferences"})
public class NameForm extends ExtensibleData implements HasFieldBasedEvidence {

  private String lang;
  private String fullText;
  private List<NamePart> parts;
  private List<EvidenceReference> fieldValueReferences;

  /**
   * The language of the conclusion. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @return The language of the conclusion.
   */
  @XmlAttribute ( namespace = XMLConstants.XML_NS_URI )
  public String getLang() {
    return lang;
  }

  /**
   * The language of the conclusion. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param lang The language of the conclusion.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * The full text of the name form.
   *
   * @return The full text of the name form.
   */
  public String getFullText() {
    return fullText;
  }

  /**
   * The full text of the name form.
   *
   * @param fullText The full text of the name form.
   */
  public void setFullText(String fullText) {
    this.fullText = fullText;
  }

  /**
   * The different parts of the name form.
   *
   * @return The different parts of the name form.
   */
  @XmlElement (name = "part")
  @JsonName ("parts")
  @JsonProperty ("parts")
  public List<NamePart> getParts() {
    return parts;
  }

  /**
   * The different parts of the name form.
   *
   * @param parts The different parts of the name form.
   */
  @JsonProperty ("parts")
  public void setParts(List<NamePart> parts) {
    this.parts = parts;
  }

  /**
   * The references to the record field values being used as evidence.
   *
   * @return The references to the record field values being used as evidence.
   */
  @XmlElement( name = "fieldValue" )
  @JsonProperty( "fieldValues" )
  @JsonName( "fieldValues" )
  public List<EvidenceReference> getFieldValueReferences() {
    return fieldValueReferences;
  }

  /**
   * The references to the record field values being used as evidence.
   *
   * @param fieldValueReferences The references to the record field values being used as evidence.
   */
  @JsonProperty( "fieldValues" )
  public void setFieldValueReferences(List<EvidenceReference> fieldValueReferences) {
    this.fieldValueReferences = fieldValueReferences;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitNameForm(this);
  }
}
