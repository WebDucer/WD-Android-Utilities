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

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.security.auth.x500.X500Principal;

/**
 * Created by eugen on 02.02.15.
 */
public class LogHelper {
   private static final X500Principal DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");

   private static boolean _DEBUG = false;
   private static boolean _IsDebugStateRead = false;

   public static final boolean isDebuggable(Context ctx) {
      if (!_IsDebugStateRead) {
         _DEBUG = false;

         try {
            PackageInfo pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature signatures[] = pinfo.signatures;

            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            for (int i = 0; i < signatures.length; i++) {
               ByteArrayInputStream stream = new ByteArrayInputStream(signatures[i].toByteArray());
               X509Certificate cert = (X509Certificate) cf.generateCertificate(stream);
               _DEBUG = cert.getSubjectX500Principal().equals(DEBUG_DN);
               if (_DEBUG) {
                  break;
               }
            }
         } catch (PackageManager.NameNotFoundException e) {
            // debuggable variable will remain false
         } catch (CertificateException e) {
            // debuggable variable will remain false
         }
         _IsDebugStateRead = true;
      }
      return _DEBUG;
   }

   /**
    * Log formatted message for DEBUG level
    *
    * @param tag          Log tag
    * @param formatString Log formating string
    * @param formatParams Log formating parameters
    * @return 0: not logged
    */
   public final static int d(String tag, String formatString, Object... formatParams) {

      if (_DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
         if (formatParams != null && formatParams.length > 0) {
            return Log.d(tag, String.format(formatString, formatParams));
         } else {
            return Log.d(tag, formatString);
         }
      } else {
         return 0;
      }
   }

   /**
    * Log message and exception for DEBUG level
    *
    * @param tag       Log tag
    * @param message   Log message
    * @param exception Exception to log
    * @return 0: not logged
    */
   public final static int d(String tag, String message, Throwable exception) {

      if (_DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
         return Log.d(tag, message, exception);
      } else {
         return 0;
      }
   }

   /**
    * Log formatted message for INFO level
    *
    * @param tag          Log tag
    * @param formatString Log formating string
    * @param formatParams Log formating parameters
    * @return 0: not logged
    */
   public final static int i(String tag, String formatString, Object... formatParams) {
      if (Log.isLoggable(tag, Log.INFO)) {
         if (formatParams != null && formatParams.length > 0) {
            return Log.i(tag, String.format(formatString, formatParams));
         } else {
            return Log.i(tag, formatString);
         }
      } else {
         return 0;
      }
   }

   /**
    * Log message and exception for INFO level
    *
    * @param tag       Log tag
    * @param message   Log message
    * @param exception Exception to log
    * @return 0: not logged
    */
   public final static int i(String tag, String message, Throwable exception) {
      if (Log.isLoggable(tag, Log.INFO)) {
         return Log.i(tag, message, exception);
      } else {
         return 0;
      }
   }

   /**
    * Log formatted message for ERROR level
    *
    * @param tag          Log tag
    * @param formatString Log message format string
    * @param formatParams Log message parameters
    * @return 0: not logged
    */
   public final static int e(String tag, String formatString, Object... formatParams) {
      if (Log.isLoggable(tag, Log.ERROR)) {
         if (formatParams != null && formatParams.length > 0) {
            return Log.e(tag, String.format(formatString, formatParams));
         } else {
            return Log.e(tag, formatString);
         }
      } else {
         return 0;
      }
   }

   /**
    * Log message and exception for ERROR level
    *
    * @param tag       Log tag
    * @param message   Log message
    * @param exception Exception to log
    * @return 0: not logged
    */
   public final static int e(String tag, String message, Throwable exception) {
      if (Log.isLoggable(tag, Log.ERROR)) {
         return Log.i(tag, message, exception);
      } else {
         return 0;
      }
   }
}
