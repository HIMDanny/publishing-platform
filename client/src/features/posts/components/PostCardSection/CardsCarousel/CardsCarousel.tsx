import PostCard from '../../PostCard/PostCard';
import Slider, { Settings } from 'react-slick';
import '@assets/slick.css';
import '@assets/slick-theme.css';
import SliderArrow from './SliderArrow';
import SliderPagination from './SliderPagination';

const CardsCarousel = () => {
  const settings: Settings = {
    slidesToShow: 3,
    infinite: false,
    dots: true,
    prevArrow: <SliderArrow mode="prev" />,
    nextArrow: <SliderArrow mode="next" />,
    appendDots(dots) {
      return <SliderPagination dots={dots} />;
    },
  };

  return (
    <Slider {...settings}>
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
    </Slider>
  );
};
export default CardsCarousel;
