/*
 * Copyright (c) 2014 Azavea.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package geotrellis.engine.logic

import geotrellis.engine._

/**
 * Run a function on the result of an operation
 *
 * Functionally speaking, this represents the bind operation
 * on the Operation monad
 */
case class WithResult[A, Z](a:Op[A])(call:A => Op[Z]) extends Op[Z] {
  def _run() = runAsync(a.flatMap(call) :: Nil)
  val nextSteps:Steps = {
    case a :: Nil => Result(a.asInstanceOf[Z])
  }
}
