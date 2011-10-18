package VEW.Common.XML;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.event.EventListenerList;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * The XMLTag is the representation of an individual XML tag &lt;<I>tagname</I>&gt;.
 * 
 * The class provides support for the use of an XMLTag as a tree node, as well
 * as allowing other classes to listen for changes on the tag.
 */
public class XMLTag implements Cloneable, Comparable, XMLTagListener,
    MutableTreeNode {
  /**
   * Constant representing the amount of space that should be added for a single
   * indentation when outputting the XMLTag object as a string.
   */
  private final static String SingleIndent = "  ";
  /**
   * The XMLTag that is the parent of this tag (this is null if the current tag
   * is the root tag).
   */
  protected XMLTag ParentTag;
  /**
   * The children of this tag. This variable is mutually exclusive to the
   * TagValue tag.
   */
  protected ArrayList ChildTags = new ArrayList();
  /** The attributes related to this tag. */
  protected HashMap AttributeList = new HashMap();
  /** The name of the tag i.e. &lt;<I>Name</I>&gt;. */
  protected String TagName;
  /** The Value of the tag i.e. &lt;tag&gt;<I>Value</I>&lt;/tag&gt;. */
  protected String TagValue;
  /** The number of children this tag has. */
  protected int ChildrenCount = 0;
  /**
   * Whether or not the current tag (and it's children) should be written if the
   * parent tag is output.
   */
  protected boolean IsTemporary = false;
  /** The list of objects taht are "listening" to this tag. */
  protected EventListenerList Listeners = new EventListenerList();

  /*
   * \ Constructors. \
   */

  /** Default constructor, should not be used. */
  protected XMLTag() {
  }

  /**
   * Constructor to create an empty tag.
   * 
   * @param TagName
   *          The Name of the tag.
   */
  public XMLTag(String _TagName) {
    this(_TagName, null, null, false);
  }

  /**
   * Constructor to create a tag with a value.
   * 
   * @param TagName
   *          The name of the tag.
   * @param TagValue
   *          The value for the tag.
   */
  public XMLTag(String _TagName, String _TagValue) {
    this(_TagName, _TagValue, null, false);
  }

  /**
   * Constructor to create an empty tag, as the child of another tag.
   * 
   * @param TagName
   *          The name of the new tag.
   * @param ParentTag
   *          The tag which will be the parent of the new tag.
   */
  public XMLTag(String _TagName, XMLTag _ParentTag) {
    this(_TagName, null, _ParentTag, false);
  }

  /**
   * Constructor to create a tag with a value, as the child of another tag.
   * 
   * @param TagName
   *          The name of the new tag.
   * @param TagValue
   *          The value for the new tag.
   * @param ParentTag
   *          The tag which will be the parent of the new tag.
   */
  public XMLTag(String _TagName, String _TagValue, XMLTag _ParentTag) {
    this(_TagName, _TagValue, _ParentTag, false);
  }

  /**
   * Constructor to create an empty tag, which can be set as temporary.
   * 
   * If a tag is deemed temporary then it will occur in all searches etc. but
   * when the tag is output (with writeTag() or as part of an XMLFile) the tag
   * will not be included.
   * 
   * @param TagName
   *          The name of the new tag.
   * @param IsTemporary
   *          <CODE>true</CODE> if the tag is temporary, <CODE>false</CODE>
   *          if not.
   */
  public XMLTag(String _TagName, boolean _IsTemporary) {
    this(_TagName, null, null, _IsTemporary);
  }

  /**
   * Constructor to create a tag with a value, which can be set as temporary.
   * 
   * If a tag is deemed temporary then it will occur in all searches etc. but
   * when the tag is output (with writeTag() or as part of an XMLFile) the tag
   * will not be included.
   * 
   * @param TagName
   *          The name of the new tag.
   * @param TagValue
   *          The value of the new tag.
   * @param IsTemporary
   *          <CODE>true</CODE> if the tag is temporary, <CODE>false</CODE>
   *          if not.
   */
  public XMLTag(String _TagName, String _TagValue, boolean _IsTemporary) {
    this(_TagName, _TagValue, null, _IsTemporary);
  }

  /**
   * Constructor to create an empty tag as the child of another tag, which can
   * be set as temporary.
   * 
   * If a tag is deemed temporary then it will occur in all searches etc. but
   * when the tag is output (with writeTag() or as part of an XMLFile) the tag
   * will not be included.
   * 
   * @param TagName
   *          The value of the new tag.
   * @param ParentTag
   *          The tag which will be the parent of the new tag.
   * @param IsTemporary
   *          <CODE>true</CODE> if the tag is temporary, <CODE>false</CODE>
   *          if not.
   */
  public XMLTag(String _TagName, XMLTag _ParentTag, boolean _IsTemporary) {
    this(_TagName, null, _ParentTag, _IsTemporary);
  }

  /**
   * Constructor to create a tag with a value as the child of another tag, which
   * can be set as temporary.
   * 
   * If a tag is deemed temporary then it will occur in all searches etc. but
   * when the tag is output (with writeTag() or as part of an XMLFile) the tag
   * will not be included.
   * 
   * @param TagName
   *          The name of the new tag.
   * @param TagValue
   *          The value of the new tag.
   * @param ParentTag
   *          The tag which will be the parent of the new tag.
   * @param IsTemporary
   *          <CODE>true</CODE> if the tag is temporary, <CODE>false</CODE>
   *          if not.
   */
  public XMLTag(String _TagName, String _TagValue, XMLTag _ParentTag,
      boolean _IsTemporary) {
    this.ParentTag = _ParentTag;
    this.TagName = _TagName;
    this.TagValue = _TagValue;
    this.IsTemporary = _IsTemporary;
    if (ParentTag != null) {
      ParentTag.addTag(this);
    }
  }

  public static XMLTag[] sortTags(XMLTag[] source, String tag) {
    XMLTag[] newTags = new XMLTag[source.length];
    boolean done[] = new boolean[source.length];
    int bestSoFar = 0;
    for (int i = 0; i < done.length; i++)
      done[i] = false;
    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source.length; j++) {
        if (!done[j]) {
          bestSoFar = j;
          j = source.length;
        }
      }
      for (int j = 0; j < source.length; j++)
        if ((source[j].getValue(tag).compareToIgnoreCase(source[bestSoFar].getValue(tag)) < 0) && (!done[j])) bestSoFar = j;
      done[bestSoFar] = true;
      newTags[i] = source[bestSoFar];
    }
    return newTags;
  }

  public static void sortTagList(XMLTag list, String tagName, String criteria) {
    XMLTag[] unorderedVars = list.getTags(tagName);
    XMLTag[] orderedVars = XMLTag.sortTags(unorderedVars, criteria);
    for (int k = 0; k < unorderedVars.length; k++)
      unorderedVars[k].removeFromParent();
    for (int k = 0; k < orderedVars.length; k++)
      list.addTag(orderedVars[k]);
  }

  /*
   * \ Get Methods. \
   */

  /**
   * Fetches an attribute associated with the tag.
   * 
   * @param Name
   *          The attribute being searched for.
   * @return The value of the attribute if it exists, otherwise null.
   */
  public String getAttribute(String Name) {
    return (String) AttributeList.get(Name);
  }

  /**
   * Returns the name of the tag.
   * 
   * @return The name of the tag.
   */
  public String getName() {
    return TagName;
  }

  /**
   * Returns the value of the tag.
   * 
   * @return The value of the tag if it has one, otherwise null.
   */
  public String getValue() {
    return TagValue;
  }

  /**
   * The value of the tag defined by TagPath.
   * 
   * If TagPath ends with "@<I>value</I>" then it will attempt to return the
   * value of the attribute <I>value</I> from the tag described in the first
   * portion of TagPath.
   * 
   * @param TagPath
   *          The path to the value to be returned.
   * @return The value found at TagPath if one exists, else null.
   */
  public String getValue(String TagPath) {
    if (TagPath.startsWith("/")) {
      return getRootTag().getValue(TagPath.substring(1));
    }
    String[] TempStrings = TagPath.split("/?@");
    XMLTag Result = getTag(TempStrings[0]);
    if (Result != null && TempStrings.length > 1) {
      return Result.getAttribute(TempStrings[1]);
    } else if (Result != null) {
      return Result.getValue();
    } else {
      return null;
    }
  }

  /**
   * The value of the OccurrenceNumber-th tag defined by TagPath.
   * 
   * OccurrenceNumber begins indexing from 1.
   * 
   * If TagPath ends with "@<I>value</I>" then it will attempt to return the
   * value of the attribute <I>value</I> from the tag described in the first
   * portion of TagPath.
   * 
   * @param TagPath
   *          The path to the value to be returned.
   * @param OccurrenceNumber
   *          The occurrence of the desired tag to be looked at.
   * @return The value of the OccurrenceNumber-th TagPath, if that many exist,
   *         else null.
   */
  public String getValue(String TagPath, int OccurrenceNumber) {
    if (TagPath.startsWith("/")) {
      return getRootTag().getValue(TagPath.substring(1), OccurrenceNumber);
    }
    String[] TempStrings = TagPath.split("/?@");
    XMLTag Result = getTag(TempStrings[0], OccurrenceNumber);
    if (Result != null && TempStrings.length > 1) {
      return Result.getAttribute(TempStrings[1]);
    } else if (Result != null) {
      return Result.getValue();
    } else {
      return null;
    }
  }

  /**
   * Returns the value of the tag, unless it has none.
   * 
   * @param Default
   *          The default value to return if null.
   * @return The value of the tag if one exists, else Default.
   */
  public String getDefaultValue(String Default) {
    if (TagValue != null) {
      return TagValue;
    } else {
      return Default;
    }
  }

  /**
   * The value of the tag defined by TagPath unless it has none.
   * 
   * If TagPath ends with "@<I>value</I>" then it will attempt to return the
   * value of the attribute <I>value</I> from the tag described in the first
   * portion of TagPath.
   * 
   * @param Default
   *          The default value to return if null.
   * @param TagPath
   *          The path to the value to be returned.
   * @return The value found at TagPath if one exists, else Default.
   */
  public String getDefaultValue(String Default, String TagPath) {
    String ActualValue = getValue(TagPath);
    if (ActualValue != null) {
      return ActualValue;
    } else {
      return Default;
    }
  }

  /**
   * The value of the OccurrenceNumber-th tag defined by TagPath.
   * 
   * OccurrenceNumber begins indexing from 1.
   * 
   * If TagPath ends with "@<I>value</I>" then it will attempt to return the
   * value of the attribute <I>value</I> from the tag described in the first
   * portion of TagPath.
   * 
   * @param Default
   *          The default value to return if null.
   * @param TagPath
   *          The path to the value to be returned.
   * @param OccurrenceNumber
   *          The occurrence of the desired tag to be looked at.
   * @return The value of the OccurrenceNumber-th TagPath, if that many exist,
   *         else Default.
   */
  public String getDefaultValue(String Default, String TagPath,
      int OccurrenceNumber) {
    String ActualValue = getValue(TagPath, OccurrenceNumber);
    if (ActualValue != null) {
      return ActualValue;
    } else {
      return Default;
    }
  }

  /**
   * Returns the first descendent tag of the current tag, with the given name.
   * 
   * @param Name
   *          The name of the descendent to look for.
   * @return The first descendent with the required name, else null.
   */
  public XMLTag getDescendent(String Name) {
    if (TagValue != null) {
      return null;
    } else {
      XMLTag[] Children = getTags();
      for (int i = 0; i < Children.length; i++) {
        if (Children[i].getName().equals(Name)) {
          return Children[i];
        } else {
          XMLTag TempTag = Children[i].getDescendent(Name);
          if (TempTag != null) {
            return TempTag;
          }
        }
      }
      return null;
    }
  }

  /**
   * Returns the values of all child tags, ignoring tags with no value.
   * 
   * @return getValue() on all child tags.
   */
  public String[] getValues() {
    XMLTag[] TheTags = getTags();
    String[] Result = new String[TheTags.length];
    for (int i = 0; i < Result.length; i++) {
      Result[i] = TheTags[i].getValue();
    }
    return Result;
  }

  /**
   * Returns the values of all tags matching TagPath, ignoring tags with no
   * value.
   * 
   * If TagPath ends with "@<I>value</I>" then it will attempt to return the
   * values of the attribute <I>value</I> from the tags described in the first
   * portion of TagPath.
   * 
   * @param TagPath
   *          The path that result tags must have.
   * @return The values of all tags matching TagPath.
   */
  public String[] getValues(String TagPath) {
    if (TagPath.startsWith("/")) {
      return getRootTag().getValues(TagPath.substring(1));
    } else if (TagPath.startsWith("@")) {
      // XMLTag[] TheTags = getTags();
      String[] Result = new String[] { getAttribute(TagPath.substring(1)) };
      return Result;
    } else {
      String[] TempStrings = TagPath.split("/?@");
      XMLTag[] TheTags = getTags(TempStrings[0]);
      String[] Result = new String[TheTags.length];
      if (TempStrings.length > 1) {
        for (int i = 0; i < Result.length; i++) {
          Result[i] = TheTags[i].getAttribute(TempStrings[1]);
        }
      } else {
        for (int i = 0; i < Result.length; i++) {
          Result[i] = TheTags[i].getValue();
        }
      }
      return Result;
    }
  }

  /**
   * Returns the values of all child tags, using Default for tags with no value.
   * 
   * @param DefaultValue
   *          The default value to use for tags with no value.
   * @return The values of all tags matching TagPath.
   */
  public String[] getDefaultValues(String DefaultValue) {
    XMLTag[] TheTags = getTags();
    String[] Result = new String[TheTags.length];
    for (int i = 0; i < Result.length; i++) {
      Result[i] = TheTags[i].getValue();
      if (Result[i] == null) {
        Result[i] = DefaultValue;
      }
    }
    return Result;
  }

  /**
   * Returns the values of all tags matching TagPath, using Default for tags
   * with no value.
   * 
   * If TagPath ends with "@<I>value</I>" then it will attempt to return the
   * values of the attribute <I>value</I> from the tags described in the first
   * portion of TagPath.
   * 
   * @param DefaultValue
   *          The default value to use for tags with no value.
   * @param TagPath
   *          The path that result tags must have.
   * @return The values of all tags matching TagPath.
   */
  public String[] getDefaultValues(String DefaultValue, String TagPath) {
    if (TagPath.startsWith("/")) {
      return getRootTag().getDefaultValues(DefaultValue, TagPath.substring(1));
    }
    String[] TempStrings = TagPath.split("/?@");
    XMLTag[] TheTags = getTags(TempStrings[0]);
    String[] Result = new String[TheTags.length];
    if (TempStrings.length > 1) {
      for (int i = 0; i < Result.length; i++) {
        Result[i] = TheTags[i].getAttribute(TempStrings[1]);
        if (Result[i] == null) {
          Result[i] = DefaultValue;
        }
      }
    } else {
      for (int i = 0; i < Result.length; i++) {
        Result[i] = TheTags[i].getValue();
        if (Result[i] == null) {
          Result[i] = DefaultValue;
        }
      }
    }
    return Result;
  }

  /**
   * Returns the first tag corresponding to TagPath.
   * 
   * @param TagPath
   *          The path to the desired tag.
   * @return The first tag corresponding to TagPath if one exists, else null.
   */
  public XMLTag getTag(String TagPath) {
    if (TagPath.startsWith("/")) {
      return getRootTag().getTag(TagPath.substring(1));
    }
    if (TagPath.trim().equals("")) {
      return this;
    }
    String[] TempStrings = TagPath.split("/", 2);
    if (TempStrings.length == 1) {
      if (TagPath.equals(".")
          || (this instanceof XMLFile && TagPath.equals(TagName))) {
        return this;
      }
      if (TagPath.equals("..")) {
        return ParentTag;
      }
      XMLTag[] Result = new XMLTag[0];
      Result = (XMLTag[]) ChildTags.toArray(new XMLTag[0]);
      int CurrentIndex = 0;
      while (CurrentIndex < Result.length
          && !Result[CurrentIndex].getName().equals(TagPath)) {
        CurrentIndex++;
      }
      if (CurrentIndex < Result.length) {
        return Result[CurrentIndex];
      } else {
        return null;
      }
    } else {
      XMLTag Result;
      if (TempStrings[0].equals("*")) {
        XMLTag[] TempTags = (XMLTag[]) ChildTags.toArray(new XMLTag[0]);
        for (int i = 0; i < TempTags.length; i++) {
          Result = TempTags[i].getTag(TempStrings[1]);
          if (Result != null) {
            return Result;
          }
        }
        return null;
      } else {
        Result = getTag(TempStrings[0]);
        if (Result != null) {
          return Result.getTag(TempStrings[1]);
        } else {
          return null;
        }
      }
    }
  }

  /**
   * Returns the OccurrenceNumber-th tag corresponding to TagPath.
   * 
   * @param TagPath
   *          The path to the desired tag.
   * @param OccurrenceNumber
   *          The occurrence of the desired tag to be retrieved.
   * @return The OccurrenceNumber-th tag corresponding to TagPath if that many
   *         exist, else null.
   */
  public XMLTag getTag(String TagPath, int OccurrenceNumber) {
    if (TagPath.startsWith("/")) {
      return getRootTag().getTag(TagPath.substring(1), OccurrenceNumber);
    }
    XMLTag[] AllTags = getTags(TagPath);
    if (AllTags.length >= OccurrenceNumber) {
      return AllTags[OccurrenceNumber - 1];
    }
    // else if(AllTags.length > 0)
    // {
    // return AllTags[AllTags.length - 1];
    // }
    else {
      return null;
    }
  }

  /**
   * Returns all child tags.
   * 
   * @return All child tags.
   */
  public XMLTag[] getTags() {
    XMLTag[] Result = new XMLTag[0];
    Result = (XMLTag[]) ChildTags.toArray(new XMLTag[0]);
    return Result;
  }

  /**
   * Returns all child tags that correspond to TagPath.
   * 
   * @param TagPath
   *          The path from which to retrieve the tags.
   * @return All child tags that correspond to TagPath.
   */
  public XMLTag[] getTags(String TagPath) {
    if (TagPath.startsWith("/")) {
      return getRootTag().getTags(TagPath.substring(1));
    }
    if (TagPath.trim().equals("")) {
      return getTags();
    }
    String[] TempStrings = TagPath.split("/", 2);
    if (TempStrings.length == 1) {
      if (TagPath.equals(".")
          || (this instanceof XMLFile && TagPath.equals(TagName))) {
        return new XMLTag[] { this };
      } else if (TagPath.equals("..")) {
        if (ParentTag != null) {
          return new XMLTag[] { ParentTag };
        } else {
          return new XMLTag[0];
        }
      } else if (TagPath.equals("*")) {
        return (XMLTag[]) ChildTags.toArray(new XMLTag[0]);
      }
      ArrayList TemporaryStore = new ArrayList();
      // XMLTag[] Result = new XMLTag[0];
      for (int i = 0; i < ChildTags.size(); i++) {
        XMLTag TempTag = (XMLTag) ChildTags.get(i);
        // if (!TempTag.isTemporary()) {
        if (TempTag.getName().equals(TagPath)) {
          TemporaryStore.add(TempTag);
        }
        // }
      }
      return (XMLTag[]) TemporaryStore.toArray(new XMLTag[0]);
    } else {
      XMLTag[] Result = getTags(TempStrings[0]);
      ArrayList TemporaryStore = new ArrayList();
      for (int i = 0; i < Result.length; i++) {
        TemporaryStore.addAll(Arrays.asList(Result[i].getTags(TempStrings[1])));
      }
      return (XMLTag[]) TemporaryStore.toArray(new XMLTag[0]);
    }
  }

  /**
   * Returns all child tags that actually exist that correspond to TagPath.
   * Written in anger by Wes in response to the ridiculous Temporary Tags.
   * 
   * @param TagPath
   *          The path from which to retrieve the tags.
   * @return All child tags that correspond to TagPath.
   */
  public XMLTag[] getRealTags(String TagPath) {
    if (TagPath.startsWith("/")) {
      return getRootTag().getTags(TagPath.substring(1));
    }
    if (TagPath.trim().equals("")) {
      return getTags();
    }
    String[] TempStrings = TagPath.split("/", 2);
    if (TempStrings.length == 1) {
      if (TagPath.equals(".")
          || (this instanceof XMLFile && TagPath.equals(TagName))) {
        return new XMLTag[] { this };
      } else if (TagPath.equals("..")) {
        if (ParentTag != null) {
          return new XMLTag[] { ParentTag };
        } else {
          return new XMLTag[0];
        }
      } else if (TagPath.equals("*")) {
        return (XMLTag[]) ChildTags.toArray(new XMLTag[0]);
      }
      ArrayList TemporaryStore = new ArrayList();
      // XMLTag[] Result = new XMLTag[0];
      for (int i = 0; i < ChildTags.size(); i++) {
        XMLTag TempTag = (XMLTag) ChildTags.get(i);
        if (!TempTag.isTemporary()) {
          if (TempTag.getName().equals(TagPath)) {
            TemporaryStore.add(TempTag);
          }
        }
      }
      return (XMLTag[]) TemporaryStore.toArray(new XMLTag[0]);
    } else {
      XMLTag[] Result = getTags(TempStrings[0]);
      ArrayList TemporaryStore = new ArrayList();
      for (int i = 0; i < Result.length; i++) {
        TemporaryStore.addAll(Arrays.asList(Result[i].getTags(TempStrings[1])));
      }
      return (XMLTag[]) TemporaryStore.toArray(new XMLTag[0]);
    }
  }

  /**
   * Returns all child tags that correspond to the paths in TagPath.
   * 
   * @param TagPath
   *          The paths from which to retrieve the tags.
   * @return All child tags that correspond to one of the paths in TagPath.
   */
  public XMLTag[] getTags(String[] TagPaths) {
    String TagPath;
    XMLTag[][] Results = new XMLTag[TagPaths.length][];
    for (int q = 0; q < TagPaths.length; q++) {
      TagPath = TagPaths[q];
      if (TagPath.startsWith("/")) {
        return getRootTag().getTags(TagPath.substring(1));
      }
      if (TagPath.trim().equals("")) {
        return getTags();
      }
      String[] TempStrings = TagPath.split("/", 2);
      if (TempStrings.length == 1) {
        if (TagPath.equals(".")
            || (this instanceof XMLFile && TagPath.equals(TagName))) {
          Results[q] = new XMLTag[] { this };
        } else if (TagPath.equals("..")) {
          if (ParentTag != null) {
            Results[q] = new XMLTag[] { ParentTag };
          } else {
            Results[q] = new XMLTag[0];
          }
        } else if (TagPath.equals("*")) {
          return (XMLTag[]) ChildTags.toArray(new XMLTag[0]);
        }
        ArrayList TemporaryStore = new ArrayList();
        // XMLTag[] Result = new XMLTag[0];
        for (int i = 0; i < ChildTags.size(); i++) {
          XMLTag TempTag = (XMLTag) ChildTags.get(i);
          if (TempTag.getName().equals(TagPath)) {
            TemporaryStore.add(TempTag);
          }
        }
        Results[q] = (XMLTag[]) TemporaryStore.toArray(new XMLTag[0]);
      } else {
        XMLTag[] Result = getTags(TempStrings[0]);
        ArrayList TemporaryStore = new ArrayList();
        for (int i = 0; i < Result.length; i++) {
          TemporaryStore.addAll(Arrays
              .asList(Result[i].getTags(TempStrings[1])));
        }
        Results[q] = (XMLTag[]) TemporaryStore.toArray(new XMLTag[0]);
      }
    }
    int Counter = 0;
    for (int i = 0; i < Results.length; i++) {
      Counter += Results[i].length;
    }
    XMLTag[] Result = new XMLTag[Counter];
    Counter = 0;
    for (int i = 0; i < Results.length; i++) {
      for (int j = 0; j < Results[i].length; j++) {
        Result[Counter] = Results[i][j];
        Counter++;
      }
    }
    return Result;
  }

  /**
   * Retrieves the first tag corresponding to MainPath, where the value it
   * returns from SubPath is SearchValue.
   * 
   * If SubPath ends with "@<I>value</I>" then it will attempt to return the
   * value of the attribute <I>value</I> from the tag described in the first
   * portion of SubPath.
   * 
   * @param MainPath
   *          The path the resultant tag must correspond to.
   * @param SubPath
   *          The path from the resultant tag that equals the value in
   *          SearchValue.
   * @param SearchValue
   *          The value to match.
   * @return The first tag corresponding to MainPath, where the value it returns
   *         from SubPath is SearchValue if one exists, else null.
   */
  public XMLTag getTagWhere(String MainPath, String SubPath, String SearchValue) {
    XMLTag[] MainTags = getTags(MainPath);
    String[] TempStrings;
    for (int i = 0; i < MainTags.length; i++) {
      TempStrings = MainTags[i].getValues(SubPath);
      for (int j = 0; j < TempStrings.length; j++) {
        if (TempStrings[j] != null && TempStrings[j].equals(SearchValue)) {
          return MainTags[i];
        }
      }
    }
    return null;
  }

  /**
   * Retrieves the OccurrenceNumber-th tag corresponding to MainPath, where the
   * value it returns from SubPath is SearchValue.
   * 
   * If SubPath ends with "@<I>value</I>" then it will attempt to return the
   * value of the attribute <I>value</I> from the tag described in the first
   * portion of SubPath.
   * 
   * @param MainPath
   *          The path the resultant tag must correspond to.
   * @param SubPath
   *          The path from the resultant tag that equals the value in
   *          SearchValue.
   * @param SearchValue
   *          The value to match.
   * @param OccurrenceNumber
   *          The occurrence of the desired tag to be retrieved.
   * @return The OccurrenceNumber-th tag corresponding to MainPath, where the
   *         value it returns from SubPath is SearchValue if that many exist,
   *         else null.
   */
  public XMLTag getTagWhere(String MainPath, String SubPath,
      String SearchValue, int OccurrenceNumber) {
    XMLTag[] MainTags = getTags(MainPath);
    String[] TempStrings;
    // XMLTag LastMatch = null;
    int OccCounter = 0;
    for (int i = 0; i < MainTags.length; i++) {
      TempStrings = MainTags[i].getValues(SubPath);
      for (int j = 0; j < TempStrings.length; j++) {
        if (TempStrings[j].equals(SearchValue)) {
          OccCounter++;
          if (OccCounter == OccurrenceNumber) {
            return MainTags[i];
          } else {
            // LastMatch = MainTags[i];
            j = TempStrings.length;
          }
        }
      }
    }
    return null;
    // return LastMatch;
  }

  /**
   * Retrieves all tags corresponding to MainPath, where the value returned from
   * SubPath is SearchValue.
   * 
   * If SubPath ends with "@<I>value</I>" then it will attempt to return the
   * value of the attribute <I>value</I> from the tag described in the first
   * portion of SubPath.
   * 
   * @param MainPath
   *          The path the resultant tags must correspond to.
   * @param SubPath
   *          The path from the resultant tags that equals the value in
   *          SearchValue.
   * @param SearchValue
   *          The value to match.
   * @return All tags corresponding to MainPath, where the value returned from
   *         SubPath is SearchValue.
   */
  public XMLTag[] getTagsWhere(String MainPath, String SubPath,
      String SearchValue) {
    XMLTag[] MainTags = getTags(MainPath);
    ArrayList TempStore = new ArrayList();
    String[] TempStrings;
    for (int i = 0; i < MainTags.length; i++) {
      TempStrings = MainTags[i].getValues(SubPath);
      for (int j = 0; j < TempStrings.length; j++) {
        if (TempStrings[j]!=null) {
          if (TempStrings[j].equals(SearchValue) && !TempStore.contains(MainTags[i])) {
            TempStore.add(MainTags[i]);
          }
        }
      }
    }
    MainTags = (XMLTag[]) TempStore.toArray(new XMLTag[0]);
    return MainTags;
  }

  /**
   * Retrieves all tags corresponding to the paths in MainPath, where the value
   * returned from SubPath is SearchValue.
   * 
   * If SubPath ends with "@<I>value</I>" then it will attempt to return the
   * value of the attribute <I>value</I> from the tag described in the first
   * portion of SubPath.
   * 
   * @param MainPath
   *          The paths the resultant tags must correspond to.
   * @param SubPath
   *          The path from the resultant tags that equals the value in
   *          SearchValue.
   * @param SearchValue
   *          The value to match.
   * @return All tags corresponding to the paths in MainPath, where the value
   *         returned from SubPath is SearchValue.
   */
  public XMLTag[] getTagsWhere(String[] MainPath, String SubPath,
      String SearchValue) {
    XMLTag[] MainTags = getTags(MainPath);
    ArrayList TempStore = new ArrayList();
    String[] TempStrings;
    for (int i = 0; i < MainTags.length; i++) {
      TempStrings = MainTags[i].getValues(SubPath);
      for (int j = 0; j < TempStrings.length; j++) {
        if (TempStrings[j].equals(SearchValue)
            && !TempStore.contains(MainTags[i])) {
          TempStore.add(MainTags[i]);
        }
      }
    }
    MainTags = (XMLTag[]) TempStore.toArray(new XMLTag[0]);
    return MainTags;
  }

  /**
   * Returns the parent tag.
   * 
   * @return The parent tag.
   */
  public XMLTag getParentTag() {
    return ParentTag;
  }

  /**
   * Returns the number of child tags.
   * 
   * @return The number of child tags.
   */
  public int getNumberOfChildren() {
    return ChildrenCount;
  }

  /**
   * Returns the root tag of the XML structure.
   * 
   * @return The first ancestor to have no parent.
   */
  public XMLTag getRootTag() {
    if (ParentTag == null) {
      return this;
    } else {
      return ParentTag.getRootTag();
    }
  }

  /**
   * Returns whether or not the tag is empty.
   * 
   * @return <CODE>true</CODE> if the tag has no children or value, else
   *         <CODE>false</CODE>.
   */
  public boolean isEmpty() {
    return ChildrenCount == 0 && TagValue == null;
  }

  /**
   * Returns whether or not the tag is temporary.
   * 
   * @return <CODE>true</CODE> if the tag is temporary, else <CODE>false</CODE>.
   */
  public boolean isTemporary() {
    return IsTemporary;
  }

  /*
   * \ Add Methods. \
   */

  /**
   * Adds NewChild as a child, but doesn't alter NewChild's parent status.
   * 
   * This means that the child tag will turn up in a search of this tags
   * children, but this tag will not be locatable from the child.
   * 
   * @param NewChild
   *          The tag to share.
   * @return The real parent of NewChild.
   */
  public XMLTag shareTag(XMLTag NewChild) {
    if (NewChild != null) {
      ChildTags.add(NewChild);
      NewChild.addXMLTagListener(this);
      ChildrenCount++;
      fireTagAdded(new XMLPath());
    }
    return NewChild.getParentTag();
  }

  /**
   * Adds all of NewChildren as child tags, but doesn't alter their parent
   * status'.
   * 
   * This means that the child tags will turn up in a search of this tags
   * children, but this tag will not be locatable from the children.
   * 
   * @param NewChildren
   *          The tags to share.
   */
  public void shareTags(XMLTag[] NewChildren) {
    if (NewChildren != null && NewChildren.length > 0) {
      for (int i = 0; i < NewChildren.length; i++) {
        ChildTags.add(NewChildren[i]);
        NewChildren[i].addXMLTagListener(this);
        ChildrenCount++;
      }
      fireTagAdded(new XMLPath());
    }
  }

  /**
   * Adds NewChild as a child tag, and sets it's parent to be this tag.
   * 
   * @param NewChild
   *          The new tag to add.
   * @return The old parent of NewChild.
   */
  public XMLTag addTag(XMLTag NewChild) {
    if (NewChild != null) {
      ChildTags.add(NewChild);
      NewChild.setParent(this);
      ChildrenCount++;
      fireTagAdded(new XMLPath());
    }
    return NewChild;
  }

  /**
   * Adds all of NewChildren to this tag, and sets their parent tags to be this
   * one.
   * 
   * @param NewChildren
   *          The new tags to add.
   */
  public void addTags(XMLTag[] NewChildren) {
    if (NewChildren != null && NewChildren.length > 0) {
      for (int i = 0; i < NewChildren.length; i++) {
        ChildTags.add(NewChildren[i]);
        NewChildren[i].setParent(this);
        ChildrenCount++;
      }
      fireTagAdded(new XMLPath());
    }
  }

  /**
   * Creates a new empty tag, and adds it.
   * 
   * @param TagName
   *          The name for the new tag.
   * @return The new tag.
   */
  public XMLTag addTag(String _TagName) {
    return addTag(_TagName, null, false);
  }

  /**
   * Creates a new tag with a value, and adds it.
   * 
   * @param TagName
   *          The name for the new tag.
   * @param TagValue
   *          The value for the new tag.
   * @return The new tag.
   */
  public XMLTag addTag(String _TagName, String _TagValue) {
    return addTag(_TagName, _TagValue, false);
  }

  /**
   * Creates a new empty tag that can be temporary and adds it.
   * 
   * @param TagName
   *          The name of the new tag.
   * @param IsTemporary
   *          The temporary status of the new tag.
   * @return The new tag.
   */
  public XMLTag addTag(String _TagName, boolean _IsTemporary) {
    return addTag(_TagName, null, _IsTemporary);
  }

  /**
   * Creates a new tag with a value that may be temporary, and adds it.
   * 
   * @param TagName
   *          The name of the new tag.
   * @param TagValue
   *          The value of the new tag.
   * @param IsTemporary
   *          The temporary status of the new tag.
   * @return The new tag.
   */
  public XMLTag addTag(String _TagName, String _TagValue, boolean _IsTemporary) {
    if (_TagName != null) {
      return addTag(new XMLTag(_TagName, _TagValue, _IsTemporary));
    } else {
      return null;
    }
  }

  /**
   * Makes NewParent the parent tag of this.
   * 
   * @param NewParent
   *          The tag to add this one to.
   * @return NewParent.
   */
  public XMLTag addToTag(XMLTag NewParent) {
    NewParent.addTag(this);
    return NewParent;
  }

  /*
   * \ Remove/Replace Methods. \
   */

  /**
   * Removes this tag from its parent.
   * 
   * @return The old parent.
   */
  public XMLTag removeTagFromParent() {
    if (ParentTag == null) {
      return null;
    } else {
      XMLTag OldParent = ParentTag;
      OldParent.removeChild(this);
      return OldParent;
    }
  }

  /**
   * Removes all children from the tag.
   * 
   * @param UnwantedChild
   *          The child to be removed.
   */

  public void removeAllChildren() {
    ChildTags.clear();
    fireTagRemoved(new XMLPath());
    ChildrenCount = 0;
  }

  /**
   * Removes UnwantedChild from the child tags.
   * 
   * @param UnwantedChild
   *          The child to be removed.
   */
  public void removeChild(XMLTag UnwantedChild) {
    if (ChildTags.contains(UnwantedChild)) {
      ChildTags.remove(ChildTags.indexOf(UnwantedChild));
      if (UnwantedChild.getParentTag() == this) {
        UnwantedChild.setParent(null);
      } else {
        UnwantedChild.removeXMLTagListener(this);
      }
      ChildrenCount--;
      fireTagRemoved(new XMLPath());
    }
  }

  /**
   * Swaps the unwanted child for one that's wanted.
   * 
   * @param UnwantedChild
   *          The tag to be discarded.
   * @param WantedChild
   *          The tag to be added.
   */
  protected void exchangeChild(XMLTag UnwantedChild, XMLTag WantedChild) {
    ChildTags.set(ChildTags.indexOf(UnwantedChild), WantedChild);
  }

  /**
   * Replaces OldTag with this tag.
   * 
   * @param OldTag
   *          The tag to be replaced.
   * @return The parent tag.
   */
  public XMLTag replace(XMLTag OldTag) {
    if (OldTag == null) {
      return null;
    }
    XMLTag NewParentTag = OldTag.getParentTag();// removeTagFromParent();
    if (NewParentTag != null) {
      // NewParentTag.addTag(this);
      NewParentTag.exchangeChild(OldTag, this);
    }
    ParentTag = NewParentTag;
    OldTag.setParent(null);
    return NewParentTag;
  }

  /**
   * Replaces <CODE>ParentTag.getTag(OldTagName)</CODE> with this tag. If no
   * tag is found, then this tag is just added to ParentTag.
   * 
   * @param ParentTag
   *          The parent tag to search from.
   * @param OldTagName
   *          The path to the tag to be replaced.
   * @return The tag that has been replaced (or null if none was found).
   */
  public XMLTag replace(XMLTag _ParentTag, String OldTagName) {
    XMLTag OldTag = _ParentTag.getTag(OldTagName);
    if (OldTag != null) {
      // OldTag.removeTagFromParent().addTag(this);
      _ParentTag.exchangeChild(OldTag, this);
    } else {
      _ParentTag.addTag(this);
    }
    return OldTag;
  }

  /**
   * Replaces <CODE>ParentTag.getTag(OldTagName, OccurrenceNumber)</CODE> with
   * this tag. If not enough tags are found, then this tag is just added to
   * ParentTag.
   * 
   * @param ParentTag
   *          The parent tag to search from.
   * @param OldTagName
   *          The path to the tag to be replaced.
   * @param OccurrenceNumber
   *          The occurrence number of the path in OldTagName to be replaced.
   * @return The tag that has been replaced if one existed, else null.
   */
  public XMLTag replace(XMLTag _ParentTag, String OldTagName,
      int OccurrenceNumber) {
    XMLTag OldTag = _ParentTag.getTag(OldTagName, OccurrenceNumber);
    if (OldTag != null) {
      // OldTag.removeTagFromParent().addTag(this);
      _ParentTag.exchangeChild(OldTag, this);
    } else {
      _ParentTag.addTag(this);
    }
    return OldTag;
  }

  /*
   * \ Set Methods. \
   */

  /**
   * Sets the parent of this tag to NewParent.
   * 
   * @param NewParent
   *          The new parent tag.
   * @return The old parent tag.
   */
  protected XMLTag setParent(XMLTag NewParent) {
    if (ParentTag != null) {
      XMLTag OldParent = ParentTag;
      OldParent.removeChild(this);
      ParentTag = NewParent; /*
                               * if(NewParent != null) {
                               * addXMLTagListener(NewParent); }
                               */
      return OldParent;
    } else {
      ParentTag = NewParent; /*
                               * if(NewParent != null) {
                               * addXMLTagListener(NewParent); }
                               */
      return null;
    }
  }

  /**
   * Set the tempoarary status.
   * 
   * @param IsTemporary
   *          The new temporary status.
   */
  public void setTemporary(boolean _IsTemporary) {
    IsTemporary = _IsTemporary;
  }

  /**
   * Set the value of the tag.
   * 
   * @param TagValue
   *          The new value.
   * @return <CODE>true</CODE> if successful (i.e. the tag has no children),
   *         else <CODE>false</CODE>.
   */
  public boolean setValue(String _TagValue) {
    if (ChildrenCount == 0) {
      TagValue = _TagValue;
      fireTagChanged(new XMLPath());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Sets the attribute "Name" to the value "Value" adding the attribute if none
   * existed previously.
   * 
   * @param Name
   *          The name of the attribute.
   * @param Value
   *          The new value for the attribute.
   * @return The old value of the attribute.
   */
  public String setAttribute(String Name, String Value) {
    String ReturnValue = (String) AttributeList.get(Name);
    AttributeList.put(Name, Value);
    return ReturnValue;
  }
  
  public void removeAttribute(String Name) {
    if (AttributeList.get(Name)!=null) AttributeList.remove(Name);
  }

  /**
   * Sets the name of the tag.
   * 
   * @param NewName
   *          The new name for the tag.
   */
  public void setName(String NewName) {
    TagName = NewName;
  }

  /**
   * Sets the value for the tag/attribute found at TagPath.
   * 
   * If TagPath ends with "@<I>value</I>" then it will attempt to return the
   * values of the attribute <I>value</I> from the tags described in the first
   * portion of TagPath.
   * 
   * @param TagPath
   *          The path to the value to be returned.
   * @param _TagValue
   *          The new value.
   * @return The old value.
   */
  public boolean setValue(String TagPath, String _TagValue) {
    // Returns true if the tagpath existed, false if it was created.
    if (TagPath.startsWith("@")) {
      String TempString = getAttribute(TagPath.substring(1));
      setAttribute(TagPath.substring(1), _TagValue);
      if (TempString == null) {
        return false;
      } else {
        return true;
      }
    }
    String[] TempString = TagPath.split("/", 2);
    XMLTag TempTag = getTag(TempString[0]);
    if (TempString.length == 1) {
      if (TempTag == null) {
        addTag(TempString[0], _TagValue);
        return false;
      } else {
        return TempTag.setValue(_TagValue);
      }
    } else {
      if (TempTag == null) {
        TempTag = addTag(TempString[0]);
      }
      return TempTag.setValue(TempString[1], _TagValue);
    }
  }

  /*
   * \ File IO Methods. \
   */

  /**
   * Calls write() on the parent tag if one exists. This has the effect of
   * writing an XMLFile if one exists in the ancestory.
   * 
   * @return <CODE>true</CODE> if an XMLFile was written else <CODE>false</CODE>.
   */
  public boolean write() {
    if (ParentTag == null) {
      return false;
    } else {
      return ParentTag.write();
    }
  }

  public boolean write(OutputStream WritingStream) {
    if (ParentTag == null) {
      return false;
    } else {
      return ParentTag.write(WritingStream);
    }
  }

  public boolean writeTag(OutputStream WritingStream) {
    return writeTag(WritingStream, 0);
  }

  public boolean writeTag(OutputStream WritingStream, int Indent) {
    try {
      BufferedWriter OutputWriter = new BufferedWriter(new OutputStreamWriter(
          WritingStream));
      return writeTag(OutputWriter, Indent);
    } catch (Exception e) {
      return false;
    }
  }

  public boolean writeTag(BufferedWriter OutputWriter, int Indent) {
    System.out.println(IsTemporary);
    if (IsTemporary) {
      return false;
    }
    try {
      for (int i = 0; i < Indent; i++) {
        OutputWriter.write(SingleIndent);
      }
      OutputWriter.write("<" + TagName);
      Iterator AttIt = AttributeList.entrySet().iterator();
      Map.Entry TempEntry;
      
      while (AttIt.hasNext()) {
        TempEntry = (Map.Entry) AttIt.next();
        OutputWriter.write(" ");
        OutputWriter.write((String) TempEntry.getKey());
        OutputWriter.write("=\"");
        OutputWriter.write((String) TempEntry.getValue());
        OutputWriter.write("\"");
      }
      if (TagValue != null) {
        OutputWriter.write(">" + TagValue + "</" + TagName + ">");
      } else if (ChildrenCount != 0) {
        OutputWriter.write(">");
        for (int i = 0; i < ChildrenCount; i++) {
          ((XMLTag) ChildTags.get(i)).write(Indent + 1, OutputWriter);
        }
        OutputWriter.newLine();
        for (int i = 0; i < Indent; i++) {
          OutputWriter.write(SingleIndent);
        }
        OutputWriter.write("</" + TagName + ">");
      } else {
        OutputWriter.write(" />");
      }
      OutputWriter.flush();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  protected void write(int Indent, BufferedWriter OutputWriter)
      throws IOException {
    if (IsTemporary) {
      return;
    }
    OutputWriter.newLine();
    for (int i = 0; i < Indent; i++) {
      OutputWriter.write(SingleIndent);
    }
    OutputWriter.write("<" + TagName);
    Iterator AttIt = AttributeList.entrySet().iterator();
    Map.Entry TempEntry;
    while (AttIt.hasNext()) {
      TempEntry = (Map.Entry) AttIt.next();
      OutputWriter.write(" ");
      OutputWriter.write((String) TempEntry.getKey());
      OutputWriter.write("=\"");
      OutputWriter.write((String) TempEntry.getValue());
      OutputWriter.write("\"");
    }
    if ((TagValue != null) && (TagValue.length()>0)) {
      OutputWriter.write(">" + TagValue + "</" + TagName + ">");
    } else if (ChildrenCount != 0) {
      OutputWriter.write(">");
      for (int i = 0; i < ChildrenCount; i++) {
        ((XMLTag) ChildTags.get(i)).write(Indent + 1, OutputWriter);
      }
      OutputWriter.newLine();
      for (int i = 0; i < Indent; i++) {
        OutputWriter.write(SingleIndent);
      }
      OutputWriter.write("</" + TagName + ">");
    } else {
      OutputWriter.write(" />");
    }
  }

  /*
   * \ Tag Listener functions \
   */

  protected void fireTagAdded(XMLPath PathToTag) {
    XMLTagListener[] listeners = (XMLTagListener[]) Listeners
        .getListeners(XMLTagListener.class);
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].tagAdded(new XMLTagEvent(this, PathToTag));
    }
    if (ParentTag != null) {
      ParentTag.fireTagAdded(new XMLPath(this, PathToTag, true));
    }
  }

  protected void fireTagRemoved(XMLPath PathToTag) {
    XMLTagListener[] listeners = (XMLTagListener[]) Listeners
        .getListeners(XMLTagListener.class);
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].tagRemoved(new XMLTagEvent(this, PathToTag));
    }
    if (ParentTag != null) {
      ParentTag.fireTagRemoved(new XMLPath(this, PathToTag, true));
    }
  }

  protected void fireTagChanged(XMLPath PathToTag) {
    XMLTagListener[] listeners = (XMLTagListener[]) Listeners
        .getListeners(XMLTagListener.class);
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].tagChanged(new XMLTagEvent(this, PathToTag));
    }
    if (ParentTag != null) {
      ParentTag.fireTagChanged(new XMLPath(this, PathToTag, true));
    }
  }

  public void addXMLTagListener(XMLTagListener L) {
    Listeners.add(XMLTagListener.class, L);
  }

  public void removeXMLTagListener(XMLTagListener L) {
    Listeners.remove(XMLTagListener.class, L);
  }

  public void tagAdded(XMLTagEvent e) {
    fireTagAdded(e.getPath());
  }

  public void tagRemoved(XMLTagEvent e) {
    fireTagRemoved(e.getPath());
  }

  public void tagChanged(XMLTagEvent e) {
    fireTagChanged(e.getPath());
  }

  public int compareTo(Object OtherObject) {
    XMLTag ComparisonObject = (XMLTag) OtherObject;
    return TagName.compareTo(ComparisonObject.getName());
  }

  public Object clone() {
    XMLTag ThisClone = new XMLTag(TagName, TagValue, IsTemporary);
    ThisClone.AttributeList = (HashMap) AttributeList.clone();
    for (int i = 0; i < ChildTags.size(); i++) {
      XMLTag TagToClone = (XMLTag) ChildTags.get(i);
      ThisClone.addTag((XMLTag) TagToClone.clone());
    }
    return ThisClone;
  }

  /*
   * \ Start of MutableTreeNode Methods. \
   */

  public Enumeration children() {
    return (new Vector(ChildTags)).elements();
  }

  public boolean getAllowsChildren() {
    return TagValue == null;
  }

  public TreeNode getChildAt(int param) {
    return (TreeNode) ChildTags.get(param);
  }

  public int getChildCount() {
    return ChildrenCount;
  }

  public int getIndex(TreeNode treeNode) {
    return ChildTags.indexOf(treeNode);
  }

  public TreeNode getParent() {
    return ParentTag;
  }

  public void insert(MutableTreeNode mutableTreeNode, int param) {
    if (mutableTreeNode != null) {
      ChildTags.add(param, mutableTreeNode);
      mutableTreeNode.setParent(this);
      ChildrenCount++;
      fireTagAdded(new XMLPath());
    }
  }

  public boolean isLeaf() {
    return (ChildrenCount == 0);
  }

  public void remove(MutableTreeNode mutableTreeNode) {
    removeChild((XMLTag) mutableTreeNode);

  }

  public void remove(int param) {
    removeChild((XMLTag) ChildTags.get(param));
  }

  public void removeFromParent() {
    removeTagFromParent();
  }

  public void setParent(MutableTreeNode mutableTreeNode) {
    setParent((XMLTag) mutableTreeNode);
  }

  public void setUserObject(Object obj) {
  }
}