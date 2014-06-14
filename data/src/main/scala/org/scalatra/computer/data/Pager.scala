package org.scalatra.computer.data

/**
 * Pager
 * @author Sunghyouk Bae
 */
case class Pager[T](items: Seq[T], pageNo: Int, offset: Long, total: Long) {

  lazy val prev: Option[Int] = Option(pageNo - 1).filter(_ >= 0)
  lazy val next: Option[Int] = Option(pageNo + 1).filter(_ => (offset + items.size) < total)

}
