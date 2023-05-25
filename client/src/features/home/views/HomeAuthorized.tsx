import SearchField from '@components/UI/SearchField';
import Hero from '../components/Authorized/Hero';
import PostsFilter from '../components/Authorized/PostsFilter';
import PostsList from '../components/Authorized/PostsList';
import HighlightedPost from '@features/posts/components/HighlightedPost';
import Button from '@components/UI/Button';

const HomeAuthorized = () => {
  return (
    <main>
      <Hero />
      <div className="container mx-auto">
        <div className="mt-20 flex justify-center">
          <SearchField variant="dark" />
        </div>
        <PostsFilter />
        <div className="space-y-8">
          <PostsList />
          <HighlightedPost />
          <PostsList />
        </div>
        <div className="mt-14 mb-20 flex justify-end">
          <Button
            text="View more"
            variant="secondary"
            size="lg"
          />
        </div>
      </div>
    </main>
  );
};
export default HomeAuthorized;
