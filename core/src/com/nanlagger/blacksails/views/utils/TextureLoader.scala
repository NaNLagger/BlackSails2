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
  listTexture += ("hill" -> new TextureRegion(new Texture("data/land-2.png")))
  //listTexture += ("field" -> new TextureRegion(new Texture("data/atlas_2.png"), 513, 0, 256, 222))
  listTexture += ("field" -> new TextureRegion(new Texture("data/fielda.png")))
  listTexture += ("ocean" -> new TextureRegion(new Texture("data/ocean-3.png")))
  listTexture += ("sea" -> new TextureRegion(new Texture("data/ocean-1.png")))
  listTexture += ("reefs" -> new TextureRegion(new Texture("data/ocean-2.png")))
  listTexture += ("town" -> new TextureRegion(new Texture("data/town.png")))
  listTexture += ("port" -> new TextureRegion(new Texture("data/port.png")))
  listTexture += ("not_vision" -> new TextureRegion(new Texture("data/not_vision.png")))
  listTexture += ("coin" -> new TextureRegion(new Texture("data/coin.png")))
  listTexture += ("button" -> new TextureRegion(new Texture("data/button.png")))
  listTexture += ("button1" -> new TextureRegion(new Texture("data/button1.png")))
  listTexture += ("mainhud" -> new TextureRegion(new Texture("data/mainhud.jpg"),0, 0, 1024, 578))
  listTexture += ("expedition_ship_b" -> new TextureRegion(new Texture("data/expedition_ship.png"), 0, 0, 80, 96))
  listTexture += ("expedition_ship_t" -> new TextureRegion(new Texture("data/expedition_ship.png"), 0, 96*3, 80, 96))
  listTexture += ("expedition_ship_r" -> new TextureRegion(new Texture("data/expedition_ship.png"), 0, 96*2, 80, 96))
  listTexture += ("expedition_ship_l" -> new TextureRegion(new Texture("data/expedition_ship.png"), 0, 96, 80, 96))
  listTexture += ("fregat_ship_b" -> new TextureRegion(new Texture("data/fregat_ship.png"), 0, 0, 208, 192))
  listTexture += ("fregat_ship_t" -> new TextureRegion(new Texture("data/fregat_ship.png"), 0, 192*3, 208, 192))
  listTexture += ("fregat_ship_r" -> new TextureRegion(new Texture("data/fregat_ship.png"), 0, 192*2, 208, 192))
  listTexture += ("fregat_ship_l" -> new TextureRegion(new Texture("data/fregat_ship.png"), 0, 192, 208, 192))
  listTexture += ("black_label" -> new TextureRegion(new Texture("data/blackLabel.png")))
  listTexture += ("bgtown" -> new TextureRegion(new Texture("data/townbackground.jpg"), 0, 0, 700, 500))
  listTexture += ("backgroundship" -> new TextureRegion(new Texture("data/backgroundship.jpg")))
  listTexture += ("start2" -> new TextureRegion(new Texture("data/atlas_2.png"), 366, 513, 400, 128))

  def getTexture(name: String): TextureRegion = {
    listTexture(name)
  }
}
