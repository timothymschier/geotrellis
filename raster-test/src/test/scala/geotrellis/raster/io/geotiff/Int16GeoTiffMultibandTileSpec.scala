package geotrellis.raster.io.geotiff

import geotrellis.raster._
import geotrellis.raster.io.geotiff.writer.GeoTiffWriter
import geotrellis.raster.mapalgebra.local._

import geotrellis.vector.Extent

import geotrellis.proj4._

import geotrellis.raster.testkit._

import org.scalatest._

class Int16GeoTiffMultibandTileSpec extends FunSpec
    with Matchers
    with BeforeAndAfterAll
    with RasterMatchers
    with GeoTiffTestUtils 
    with TileBuilders {
  def p(s: String, i: String): String = 
    geoTiffPath(s"3bands/int16/3bands-${s}-${i}.tif")

  describe("Int16GeoTiffMultibandTile") {

    // Combine all bands, int

    it("should combine all bands with pixel interleave, striped") {
      val tile =
        MultibandGeoTiff(p("striped", "pixel")).tile

      val actual = tile.combine(_.sum)
      val expected = ShortRawArrayTile(Array.ofDim[Short](tile.cols * tile.rows).fill(6), tile.cols, tile.rows)

      assertEqual(actual, expected)
    }

    it("should combine all bands with pixel interleave, tiled") {
      val tile =
        MultibandGeoTiff(p("tiled", "pixel")).tile

      val actual = tile.combine(_.sum)
      val expected = ShortRawArrayTile(Array.ofDim[Short](tile.cols * tile.rows).fill(6), tile.cols, tile.rows)

      assertEqual(actual, expected)
    }

    it("should combine all bands with band interleave, striped") {
      val tile =
        MultibandGeoTiff(p("striped", "band")).tile

      val actual = tile.combine(_.sum)
      val expected = ShortRawArrayTile(Array.ofDim[Short](tile.cols * tile.rows).fill(6), tile.cols, tile.rows)

      assertEqual(actual, expected)
    }

    it("should combine all bands with band interleave, tiled") {
      val tile =
        MultibandGeoTiff(p("tiled", "band")).tile

      val actual = tile.combine(_.sum)
      val expected = ShortRawArrayTile(Array.ofDim[Short](tile.cols * tile.rows).fill(6), tile.cols, tile.rows)

      assertEqual(actual, expected)
    }
  }
}
