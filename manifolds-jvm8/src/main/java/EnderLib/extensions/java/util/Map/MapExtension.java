package EnderLib.extensions.java.util.Map;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.util.Collections;
import java.util.Map;

@Extension
public class MapExtension {
  public static <K, V> Map<K, V> immutable( @This Map<K, V> thiz ) {
    return Collections.unmodifiableMap( thiz );
  }
}