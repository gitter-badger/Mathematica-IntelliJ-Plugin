/*
 * Copyright (c) 2016 Patrick Scheibe
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.halirutan.mathematica.module;

import com.intellij.core.JavaCoreBundle;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.LanguageLevelModuleExtension;
import com.intellij.openapi.roots.LanguageLevelProjectExtension;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ArrayUtil;
import de.halirutan.mathematica.MathematicaBundle;
import de.halirutan.mathematica.sdk.MathematicaSdkType;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.lang.manifest.ManifestBundle;

/**
 * @author dsl
 * @see LanguageLevelProjectExtension
 * @see LanguageLevelModuleExtension
 */
@SuppressWarnings({"EnumeratedConstantNamingConvention", "WeakerAccess"})
public enum MathematicaLanguageLevel {
  M_11("11", MathematicaBundle.message("language.level.11")),
  M_10_4("10.4", MathematicaBundle.message("language.level.10.4")),
  M_10_3("10.3", MathematicaBundle.message("language.level.10.3")),
  M_10_2("10.2", MathematicaBundle.message("language.level.10.2")),
  M_10_1("10.1", MathematicaBundle.message("language.level.10.1")),
  M_10("10",MathematicaBundle.message("language.level.10")),
  M_9("9", MathematicaBundle.message("language.level.9")),
  M_8("8", MathematicaBundle.message("language.level.8"));


  public static final MathematicaLanguageLevel HIGHEST = M_11;
  public static final Key<MathematicaLanguageLevel> KEY = Key.create("MATHEMATICA_LANGUAGE_LEVEL");

  private final String myName;
  private final String myPresentableText;
  private final double myVersionNumber;
  MathematicaLanguageLevel(@NotNull String name, @NotNull @Nls String presentableText) {
    myName = name;
    myPresentableText = presentableText;
    myVersionNumber = Double.parseDouble(name);
  }

  public static MathematicaLanguageLevel createFromSdk(@NotNull Sdk sdk) {
    if (sdk.getSdkType() instanceof MathematicaSdkType) {
      final String version = sdk.getVersionString();
      if(version != null) {
        if (version.matches("11.*")) return M_11;
        if (version.matches("10\\.4.*")) return M_10_4;
        if (version.matches("10\\.3.*")) return M_10_3;
        if (version.matches("10\\.2.*")) return M_10_2;
        if (version.matches("10\\.1.*")) return M_10_1;
        if (version.matches("9.*")) return M_9;
        if (version.matches("8.*")) return M_8;
      }
    }
    return M_11;
  }

  @NotNull
  public String getName() {
    return myName;
  }

  public double getVersionNumber() {
    return myVersionNumber;
  }

  @NotNull
  @Nls
  public String getPresentableText() {
    return myPresentableText;
  }

  public boolean isAtLeast(@NotNull MathematicaLanguageLevel level) {
    return compareTo(level) >= 0;
  }

  public boolean isLessThan(@NotNull MathematicaLanguageLevel level) {
    return compareTo(level) < 0;
  }
}