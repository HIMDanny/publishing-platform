import HighlightedPost from '@features/posts/components/HighlightedPost';
import PostCardSection from '@features/posts/components/PostCardSection/PostCardSection';
import Hero from '../components/Unauthorized/Hero';

const HomeUnauthorized = () => {
  return (
    <main>
      <Hero />
      <section className="container mx-auto mt-40 ">
        <HighlightedPost />
        <PostCardSection title="Trending" />
      </section>
    </main>
  );
};
export default HomeUnauthorized;
