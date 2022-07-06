package EnderLib.extensions.java.util.List;

import com.enderzombi102.enderlib.ListUtil;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Extension
public class ListExtension {

	/**
	 * Creates an immutable copy of this list
	 * @return an immutable copy
	 */
	public static <E> List<E> immutable( @This List<E> thiz ) {
		return Collections.unmodifiableList( thiz );
	}

	/**
	 * Inline operation that adds all members of an array to this list
	 * @param objs objects to add
	 * @return this
	 */
	@SafeVarargs
	public static <E> List<E> addAll( @This List<E> thiz, @NotNull E... objs ) {
		return ListUtil.append( thiz, objs );
	}

	/**
	 * Creates a new list with the contents of this plus other
	 * @param other list to sum
	 * @return a new list with the contents of the two
	 */
	@Contract("_, _ -> new")
	public static <E> @NotNull List<E> plus( @This List<E> thiz, @NotNull List<E> other ) {
		return new ArrayList<>() {{
			addAll( thiz );
			addAll( other );
		}};
	}

	/**
	 * Creates a new list with the contents of this plus other
	 * @param others objects to add to sum
	 * @return a new list with the contents of the two
	 */
	@SafeVarargs
	@Contract("_, _ -> new")
	public static <E> @NotNull List<E> plus( @This List<E> thiz, @NotNull E... others ) {
		return new ArrayList<>() {{
			addAll( thiz );
			addAll( others );
		}};
	}
}