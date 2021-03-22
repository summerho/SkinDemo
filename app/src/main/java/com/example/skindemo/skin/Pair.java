/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.skindemo.skin;

/**
 * Container to ease passing around a tuple of two objects. This object provides a sensible
 * implementation of equals(), returning true if equals() is true on each of the contained
 * objects.
 * @param <F> the first object
 * @param <S> the second object
 */
public class Pair<F, S> {
    /** the first object of the pair. */
    public final F mFirst;
    /** the second object of the pair. */
    public final S mSecond;

    /**
     * Constructor for a Pair. If either are null then equals() and hashCode() will throw
     * a NullPointerException.
     * @param first the first object in the Pair
     * @param second the second object in the pair
     */
    public Pair(F first, S second) {
        this.mFirst = first;
        this.mSecond = second;
    }

    /**
     * Checks the two objects for equality by delegating to their respective equals() methods.
     * @param o the Pair to which this one is to be checked for equality
     * @return true if the underlying objects of the Pair are both considered equals()
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        
        if (!(o instanceof Pair)) {
            return false;
        }
        
        final Pair<F, S> other;
        try {
            other = (Pair<F, S>) o;
        } catch (ClassCastException e) {
            return false;
        }
        return mFirst.equals(other.mFirst) && mSecond.equals(other.mSecond);
    }

    /**
     * Compute a hash code using the hash codes of the underlying objects.
     * @return a hashcode of the Pair.
     */
    public int hashCode() {
        final int PRIME = 31;
        
        int result = 1; 
        result = PRIME * result + mFirst.hashCode();
        result = PRIME * result + mSecond.hashCode();
        return result;
    }

    /**
     * Convenience method for creating an appropriately typed pair.
     * @param <A> the firt object's Class Type
     * @param <B> the second object's Class Type
     * @param a the first object in the Pair
     * @param b the second object in the pair
     * @return a Pair that is templatized with the types of a and b
     */
    public static <A, B> Pair<A, B> create(A a, B b) {
        return new Pair<A, B>(a, b);
    }
}