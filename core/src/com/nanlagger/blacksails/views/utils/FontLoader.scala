package com.nanlagger.blacksails.views.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter

/**
 * Created by NaNLagger on 08.05.15.
 * @author Stepan Lyashenko
 */
object FontLoader {

  def getFont(size: Int, color: Color = new Color(1, 1, 1, 1f)) = {
    val generator: FreeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("data/font/TrebuchetMSRegular.ttf"))
    val parameter: FreeTypeFontParameter = new FreeTypeFontParameter()
    parameter.size = size
    parameter.color = color
    val font: BitmapFont = generator.generateFont(parameter)
    generator.dispose()
    font
  }

  def getGenerator() = {
    new FreeTypeFontGenerator(Gdx.files.internal("data/font/TrebuchetMSRegular.ttf"))
  }
}
