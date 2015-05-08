package com.nanlagger.blacksails.views.utils

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

import scala.collection.mutable

/**
 * Created by NaNLagger on 06.04.15.
 * @author Stepan Lyashenko
 */
object TextureLoader {
  val listTexture = new mutable.HashMap[String, TextureRegion]()

  listTexture += ("background" -> new TextureRegion(new Texture("data/atlas_1.png"), 0, 0, 1024, 640))
  listTexture += ("start" -> new TextureRegion(new Texture("data/atlas_1.png"), 257, 641, 256, 256))
  listTexture += ("ship" -> new TextureRegion(new Texture("data/atlas_1.png"), 0, 641, 256, 256))
  listTexture += ("fog" -> new TextureRegion(new Texture("data/atlas_1.png"), 514, 641, 256, 255))
  listTexture += ("pause" -> new TextureRegion(new Texture("data/atlas_1.png"), 900, 641, 90, 98))
  listTexture += ("fish" -> new TextureRegion(new Texture("data/atlas_2.png"), 767, 446, 256, 222))
  //listTexture += ("land" -> new TextureRegion(new Texture("data/atlas_2.png"), 513, 223, 256, 222))
  listTexture += ("land" -> new TextureRegion(new Texture("data/land-1.png")))
  //listTexture += ("field" -> new TextureRegion(new Texture("data/atlas_2.png"), 513, 0, 256, 222))
  listTexture += ("field" -> new TextureRegion(new Texture("data/fielda.png")))
  listTexture += ("ocean" -> new TextureRegion(new Texture("data/ocean-3.png")))
  listTexture += ("town" -> new TextureRegion(new Texture("data/town.png")))
  listTexture += ("port" -> new TextureRegion(new Texture("data/port.png")))
  listTexture += ("not_vision" -> new TextureRegion(new Texture("data/not_vision.png")))
  listTexture += ("start2" -> new TextureRegion(new Texture("data/atlas_2.png"), 366, 513, 400, 128))

  def getTexture(name: String): TextureRegion = {
    listTexture(name)
  }
}
