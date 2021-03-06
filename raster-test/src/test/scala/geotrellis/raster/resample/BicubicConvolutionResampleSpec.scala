package geotrellis.raster.resample

import geotrellis.raster._
import geotrellis.vector.Extent

import org.scalatest._

/**
  * Since cubic convolution resample inherits from cubic resample it
  * is only needed to test the actual math, the rest is tested in the bicubic
  * resample spec, the cubic resample spec and the bilinear
  * resample spec.
  *
  * It is also known that the bicubic resample resolves, if the cube
  * is D * D points, first D rows then the D values of each resample
  * result, and resample them. So only one dimensional resample
  * is needed to be tested.
  *
  * The bicubic convolution resample class uses a helper class,
  * which is the only class tested here.
  */
class BicubicConvolutionResampleSpec
    extends FunSuite with Matchers {

  val E = 1e-4

  val ip = new CubicConvolutionResample()

  /*
   * These test cases are derived from using a MATLAB code snippet called
   * cubiconv.m found here:
   * http://www.mathworks.com/matlabcentral/fileexchange/36800-interpolation-utilities/content/cubiconv.m
   *
   * The only difference is that the MATLAB function uses a 1.XX prefix and we a
   * 0.XX prefix for the x parameter.
   */
  test("one dimensional cubic convolution should work as expected") {
    ip.resample(Array(0, 0, 0, 0), 0) should be (0.0 +- E)
    ip.resample(Array(0, 1, 2, 3), 0.5) should be (1.5 +- E)
    ip.resample(Array(6, 8, 2, 7), 0.5) should be (4.8125 +- E)
    ip.resample(Array(-4, 65.5, -13, -5), 0.33) should be (47.4015 +- E)
    ip.resample(Array(100, 0, -100, 137), 0.89) should be (-103.6816 +- E)
    ip.resample(Array(0.65, 0.91, 12, 43), 0.52) should be (4.736 +- E)
    ip.resample(Array(1e2, 2e2, 1e2, 0), 0.17) should be (194.7113 +- E)
    ip.resample(Array(-20, -20, 1e2, 0), 0.85) should be (92.7738 +- E)
    ip.resample(Array(5, 3, 8, 12), 0.61) should be (5.7978 +- E)
    ip.resample(Array(-1, -10, -3, -6), 0.23) should be (-9.2773 +- E)
  }

}
