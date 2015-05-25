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

import junit.framework.Assert;
import junit.framework.TestCase;

public class TextHelperTests extends TestCase {
   // isNullEmptyOrWhitespace
   public final void testIsNullEmptyOrWhitespace_WithNull_ReturnTrue() {

      final boolean actual = TextHelper.isNullEmptyOrWhitespace(null);

      Assert.assertTrue(actual);
   }

   public final void testIsNullEmptyOrWhitespace_WithEmptyString_ReturnTrue() {

      final boolean actual = TextHelper.isNullEmptyOrWhitespace("");

      Assert.assertTrue(actual);
   }

   public final void testIsNullEmptyOrWhitespace_WithEmptyConstant_ReturnTrue() {

      final boolean actual = TextHelper.isNullEmptyOrWhitespace(TextHelper.EMPTY);

      Assert.assertTrue(actual);
   }

   public final void testIsNullEmptyOrWhitespace_WithSpace_ReturnTrue() {

      final boolean actual = TextHelper.isNullEmptyOrWhitespace(" ");

      Assert.assertTrue(actual);
   }

   public final void testIsNullEmptyOrWhitespace_WithTabStop_ReturnTrue() {

      final boolean actual = TextHelper.isNullEmptyOrWhitespace("\t");

      Assert.assertTrue(actual);
   }

   public final void testIsNullEmptyOrWhitespace_WithNewLine_ReturnTrue() {

      // Linux new line
      boolean actual = TextHelper.isNullEmptyOrWhitespace("\n");

      Assert.assertTrue(actual);

      // OS new line
      actual = TextHelper.isNullEmptyOrWhitespace("\r");

      Assert.assertTrue(actual);

      // Windows new line
      actual = TextHelper.isNullEmptyOrWhitespace("\n\r");

      Assert.assertTrue(actual);
   }

   public final void testIsNullEmptyOrWhitespace_WithLetter_ReturnFalse() {

      final boolean actual = TextHelper.isNullEmptyOrWhitespace("x");

      Assert.assertFalse(actual);
   }

   public final void testIsNullEmptyOrWhitespace_WithNumber_ReturnFalse() {

      final boolean actual = TextHelper.isNullEmptyOrWhitespace("9");

      Assert.assertFalse(actual);
   }

   public final void testIsNullEmptyOrWhitespace_WithDash_ReturnFalse() {

      boolean actual = TextHelper.isNullEmptyOrWhitespace("_");

      Assert.assertFalse(actual);

      actual = TextHelper.isNullEmptyOrWhitespace("-");

      Assert.assertFalse(actual);
   }

   // isEqualNoValue
   public final void testIsEqualNoValue_WithBothNullValues_ReturnTrue() {
      final boolean actual = TextHelper.isEqualNoValue(null, null);

      Assert.assertTrue(actual);
   }

   public final void testIsEqualNoValue_WithNullAndEmpty_ReturnTrue() {
      boolean actual = TextHelper.isEqualNoValue(null, TextHelper.EMPTY);

      Assert.assertTrue(actual);

      actual = TextHelper.isEqualNoValue(TextHelper.EMPTY, null);

      Assert.assertTrue(actual);
   }

   public final void testIsEqualNoValue_WithBothEmpty_ReturnTrue() {
      boolean actual = TextHelper.isEqualNoValue(null, TextHelper.EMPTY);

      Assert.assertTrue(actual);

      actual = TextHelper.isEqualNoValue(TextHelper.EMPTY, TextHelper.EMPTY);

      Assert.assertTrue(actual);
   }

   public final void testIsEqualNoValue_WithBothWhiteSpace_ReturnTrue() {
      final boolean actual = TextHelper.isEqualNoValue("\n", "\r\n");

      Assert.assertTrue(actual);
   }

   public final void testIsEqualNoValue_WithEqualValues_ReturnTrue() {
      final String value1 = "Test VALUE";
      final String value2Part1 = "Test ";
      final String value2Part2 = "VALUE";

      final boolean actual = TextHelper.isEqualNoValue(value1, value2Part1 + value2Part2);

      Assert.assertTrue(actual);
   }

   public final void testIsEqualNoValue_WithDifferentValues_ReturnFalse() {
      final String value1 = "Test VALUE";
      final String value2 = "Test Value";

      boolean actual = TextHelper.isEqualNoValue(null, value1);

      Assert.assertFalse(actual);

      actual = TextHelper.isEqualNoValue(TextHelper.EMPTY, value1);

      Assert.assertFalse(actual);

      actual = TextHelper.isEqualNoValue(value1, value2);

      Assert.assertFalse(actual);
   }

   // getTag
   public final void testGetTag_WithNullClassName_ThrowsException() {
      boolean exceptionThrown = false;

      try {
         final String tag = TextHelper.getTag(null);
      } catch (IllegalArgumentException ex) {
         exceptionThrown = true;
      }

      Assert.assertTrue(exceptionThrown);
   }

   public final void testGetTag_WithEmptyClassName_ThrowsException() {
      boolean exceptionThrown = false;

      try {
         final String tag = TextHelper.getTag("                                             ");
      } catch (IllegalArgumentException ex) {
         exceptionThrown = true;
      }

      Assert.assertTrue(exceptionThrown);
   }

   public final void testGetTag_WithLongClassName_ReturnTrimmedClassName() {
      final String className = "com.example.android.app.tests.utilities.SomeLongClassNameInIt";

      final String tag = TextHelper.getTag(className);

      final int expectedLength = 23;
      final String expectedTag = "s.SomeLongClassNameInIt";

      Assert.assertFalse(className.equals(tag));

      Assert.assertEquals(expectedLength, tag.length());

      Assert.assertEquals(expectedTag, tag);
   }

   public final void testGetTag_WithShortClassName_ReturnClassName() {
      final String className = "SomeShortClassName";

      final String tag = TextHelper.getTag(className);

      Assert.assertTrue(className.equals(tag));
   }

   public final void testGetTag_WithNullPrefix_ReturnDefaultPrefix() {
      final String className = "com.example.android.app.tests.utilities.SomeLongClassNameInIt";

      final String defaultTag = TextHelper.getTag(className);

      final String actual = TextHelper.getTag(null, className);

      Assert.assertEquals(defaultTag, actual);
   }

   public final void testGetTag_WithToLongPrefix_ThrowsException() {
      final String className = "com.example.android.app.tests.utilities.SomeLongClassNameInIt";

      final String prefix = "MyLongPrefix";

      boolean exceptionThrown = false;

      try {
         final String actual = TextHelper.getTag(prefix, className);
      } catch (StringIndexOutOfBoundsException ex) {
         exceptionThrown = true;
      }

      Assert.assertTrue(exceptionThrown);
   }

   public final void testGetTag_WithPrefix_ReturnTag() {
      final String className = "com.example.android.app.tests.utilities.SomeLongClassNameInIt";
      final String prefix = "MPX.";

      final String actual = TextHelper.getTag(prefix, className);

      final int expectedLength = 23;
      final String expectedEnding = "meLongClassNameInIt";

      Assert.assertEquals(expectedLength, actual.length());
      Assert.assertTrue(actual.startsWith(prefix));
      Assert.assertTrue(actual.endsWith(expectedEnding));
   }
}
