package kr.ac.hansung.cse.ecommercespringrest.util;

import java.util.Collection;

public class CollectionCheck {
    /**
     * Null-safe check if the specified collection is empty.
     *
     * @param coll  the collection to check, may be null
     * @return true if empty or null
     * @since Commons Collections 3.2
     */
    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }
}

