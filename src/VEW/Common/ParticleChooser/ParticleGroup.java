package VEW.Common.ParticleChooser;

import VEW.Common.XML.XMLTag;
import gnu.trove.TLongHashSet;

public class ParticleGroup {

  private String functionalGroup;
  private String groupName;
  private TLongHashSet particles;
  private TLongHashSet descendants;
  
  public ParticleGroup() {
  }

  public ParticleGroup(String group, String name, TLongHashSet _particles, TLongHashSet _descendants) {
    functionalGroup = group;
    groupName = name;
    particles = _particles;
    descendants = _descendants;
  }

  public ParticleGroup(String group, String name, long[] _particles, long[] _descendants) {
      functionalGroup = group;
      groupName = name;
      if (_particles != null)
        particles = new TLongHashSet(_particles);
      if (_descendants != null)
        descendants = new TLongHashSet(_descendants);
  }
  
  public ParticleGroup(String _functionalGroup, XMLTag tag) {
    if (!tag.getName().equals("group"))
      throw new IllegalArgumentException("Expecting 'group' tag, but found '" + tag.getName() + "'");
    
    String[] particleStrings = tag.getValues("id");
    particles = new TLongHashSet(particleStrings.length);
    for (int i = 0; i < particleStrings.length; i++)
      particles.add(Long.parseLong(particleStrings[i]));
    
    String[] descendantStrings = tag.getValues("descendants/id");
    if (descendantStrings != null && descendantStrings.length > 0) {
      descendants = new TLongHashSet(descendantStrings.length);
      for (int i = 0; i < descendantStrings.length; i++)
        descendants.add(Long.parseLong(descendantStrings[i]));
    }
    
    functionalGroup = _functionalGroup;
    this.groupName = tag.getAttribute("name");
  }

  public TLongHashSet getDescendants() {
    return descendants;
  }

  public void setDescendants(TLongHashSet _descendants) {
    descendants = _descendants;
  }

  public String getFunctionalGroup() {
    return functionalGroup;
  }

  public void setFunctionalGroup(String _functionalGroup) {
    functionalGroup = _functionalGroup;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String _groupName) {
    groupName = _groupName;
  }

  public TLongHashSet getParticles() {
    return particles;
  }

  public void setParticles(TLongHashSet _particles) {
    particles = _particles;
  }
  
  public XMLTag toXML() {
    XMLTag tag = new XMLTag("group");
    tag.setAttribute("name", groupName);
    
    long[] array = particles.toArray();
    for (int i = 0; i < array.length; i++)
      tag.addTag("id", Long.toString(array[i]));
    
    if (descendants != null && descendants.size() > 0) {
      XMLTag descendantsTag = tag.addTag("descendants");
      array = descendants.toArray();
      for (int i = 0; i < array.length; i++)
        descendantsTag.addTag("id", Long.toString(array[i]));
    }
    
    return tag;
  }

}
