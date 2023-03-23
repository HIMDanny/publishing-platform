import HighlightedPost from '@features/posts/components/HighlightedPost';
import Hero from '../components/Hero/HeroUnauthorized';

const HomeUnauthorized = () => {
  return (
    <>
      <Hero />
      <section className="container mx-auto mt-40 ">
        <HighlightedPost />
      </section>
    </>
  );
};
export default HomeUnauthorized;
