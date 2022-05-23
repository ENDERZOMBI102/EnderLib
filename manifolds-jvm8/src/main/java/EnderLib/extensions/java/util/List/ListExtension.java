package EnderLib.extensions.java.util.List;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.util.Collections;
import java.util.List;

@Extension
public class ListExtension {
  public static <E> List<E> immutable( @This List<E> thiz ) {
    return Collections.unmodifiableList( thiz );
  }
}