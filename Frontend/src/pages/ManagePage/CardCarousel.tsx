import ShuttleCard from '@components/Shuttle/ShuttleCard'

import ColorPalette from '@style/ColorPalette'
import { FaCircleChevronLeft, FaCircleChevronRight } from 'react-icons/fa6'
import Slider from 'react-slick'
import 'slick-carousel/slick/slick-theme.css'
import 'slick-carousel/slick/slick.css'

interface Props {
  show: number
}

export default function CardCarousel({ show }: Props) {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: show,
    slidesToScroll: 2,
    prevArrow: <FaCircleChevronLeft color={`${ColorPalette['lightgreen']}`} />,
    nextArrow: <FaCircleChevronRight color={`${ColorPalette['lightgreen']}`} />,
  }
  return (
    <div className="slider-container">
      <Slider {...settings}>
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
        <ShuttleCard />
      </Slider>
    </div>
  )
}
