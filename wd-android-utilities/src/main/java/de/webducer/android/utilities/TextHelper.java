/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Eugen [WebDucer] Richter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.webducer.android.utilities;

/**
 * Text helper
 *
 * @author WebDucer - IT &amp; Internet Service
 * @version 0.1
 * @since 2015-02-23
 */
public final class TextHelper {
   public final static String EMPTY = "";
   private final static int _MAX_TAG_LENGTH = 23;
   private final static int _MAX_PREFIX_LENGTH = 5;

   /**
    * Compare two strings with null as equal comparison
    *
    * @param firstValue first value to compare
    * @param secondValue second value to compare
    * @return TRUE, values are equal or both are NULL
    */
   public final static boolean isEqualNoValue(String firstValue, String secondValue) {
      if (isNullEmptyOrWhitespace(firstValue) && isNullEmptyOrWhitespace(secondValue)) {
         return true;
      }

      return firstValue != null ? firstValue.equals(secondValue) : false;
   }

   /**
    * Chec if the string is NULL, Empty or White Space
    *
    * @param value value to check
    * @return FALSE, value is not NULL, Empty or White Space
    */
   public final static boolean isNullEmptyOrWhitespace(String value) {
      return value == null || EMPTY.equals(value.trim());
   }

   /**
    * Get valid tag for logging from the given class name and prefix
    *
    * @param prefix prefix for the tag
    * @param className class name to use for tag generation
    * @return prefix + trimmed class name, if longer than allowed
    */
   public final static String getTag(String prefix, String className) {
      if (isNullEmptyOrWhitespace(className)) {
         throw new IllegalArgumentException("className can not be empty or null");
      }

      if (prefix != null && prefix.length() > _MAX_PREFIX_LENGTH) {
         throw new StringIndexOutOfBoundsException("prefix should be shorter or equal than 5 characters");
      }

      if (isNullEmptyOrWhitespace(prefix)) {
         return getTag(className);
      }

      final int classNameLength = _MAX_TAG_LENGTH - prefix.length();

      return prefix + (className.length() > classNameLength ? className.substring(className.length() - classNameLength) : className);
   }

   /**
    * Get valid tag for logging from the given class name
    *
    * @param className class name to use for tag generation
    * @return class name trimmed (the start) to the maximum allowed length for tag
    */
   public final static String getTag(String className) {
      if (isNullEmptyOrWhitespace(className)) {
         throw new IllegalArgumentException("className can not be empty or null");
      }

      return className.length() > _MAX_TAG_LENGTH ? className.substring(className.length() - _MAX_TAG_LENGTH) : className;
   }
}
