import HighlightedPost from '@features/posts/components/HighlightedPost';
import PostCardSection from '@features/posts/components/PostCardSection/PostCardSection';
import Hero from '../components/Hero/HeroUnauthorized';

const HomeUnauthorized = () => {
  return (
    <>
      <Hero />
      <section className="container mx-auto mt-40 ">
        <HighlightedPost />
        <PostCardSection title="Trending" />
      </section>
    </>
  );
};
export default HomeUnauthorized;
