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
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.*;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.source.ReferencesSources;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * A genealogical conclusion.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "Conclusion", propOrder = { "attribution", "sources", "analysis", "notes" })
public abstract class Conclusion extends HypermediaEnabledData implements Attributable, ReferencesSources, HasNotes {

  private String lang;
  private URI confidence;
  private List<SourceReference> sources;
  private List<Note> notes;
  private Attribution attribution;
  private ResourceReference analysis;

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
   * The level of confidence the contributor has about the data.
   *
   * @return The level of confidence the contributor has about the data.
   */
  @XmlAttribute
  public URI getConfidence() {
    return confidence;
  }

  /**
   * The level of confidence the contributor has about the data.
   *
   * @param confidence The level of confidence the contributor has about the data.
   */
  public void setConfidence(URI confidence) {
    this.confidence = confidence;
  }

  /**
   * The value of a the known confidence level, or {@link org.gedcomx.types.ConfidenceLevel#OTHER} if not known.
   *
   * @return The value of a the known confidence level, or {@link org.gedcomx.types.ConfidenceLevel#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public ConfidenceLevel getKnownConfidenceLevel() {
    return getConfidence() == null ? null : ConfidenceLevel.fromQNameURI(getConfidence());
  }

  /**
   * Set the confidence level from a known enumeration of confidence levels.
   *
   * @param level The known level.
   */
  @JsonIgnore
  public void setKnownConfidenceLevel(ConfidenceLevel level) {
    setConfidence(level == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(level)));
  }

  /**
   * The source references for a conclusion.
   *
   * @return The source references for a conclusion.
   */
  @XmlElement (name="source")
  @JsonProperty ("sources")
  @JsonName ("sources")
  public List<SourceReference> getSources() {
    return sources;
  }

  /**
   * The source references for a conclusion.
   *
   * @param sourceReferences The source references for a conclusion.
   */
  @JsonProperty("sources")
  public void setSources(List<SourceReference> sourceReferences) {
    this.sources = sourceReferences;
  }

  /**
   * Add a source reference.
   *
   * @param source The source reference to be added.
   */
  public void addSource(SourceReference source) {
    if (source != null) {
      if (sources == null) {
        sources = new ArrayList<SourceReference>();
      }
      sources.add(source);
    }
  }

  /**
   * Notes about a person.
   *
   * @return Notes about a person.
   */
  @XmlElement (name = "note")
  @JsonProperty ("notes")
  @JsonName ("notes")
  public List<Note> getNotes() {
    return notes;
  }

  /**
   * Notes about a person.
   *
   * @param notes Notes about a person.
   */
  @JsonProperty ("notes")
  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  /**
   * Add a note.
   *
   * @param note The note to be added.
   */
  public void addNote(Note note) {
    if (note != null) {
      if (notes == null) {
        notes = new ArrayList<Note>();
      }
      notes.add(note);
    }
  }

  /**
   * Attribution metadata for a conclusion.
   *
   * @return Attribution metadata for a conclusion.
   */
  @Override
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * Attribution metadata for a conclusion.
   *
   * @param attribution Attribution metadata for a conclusion.
   */
  @Override
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * A reference to the analysis document explaining the analysis that went into this conclusion.
   *
   * @return A reference to the analysis document explaining the analysis that went into this conclusion.
   */
  public ResourceReference getAnalysis() {
    return analysis;
  }

  /**
   * A reference to the analysis document explaining the analysis that went into this conclusion.
   *
   * @param analysis A reference to the analysis document explaining the analysis that went into this conclusion.
   */
  public void setAnalysis(ResourceReference analysis) {
    this.analysis = analysis;
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (getId() == null) ? "" : getId();
  }

}
