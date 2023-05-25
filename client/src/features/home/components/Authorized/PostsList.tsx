import PostCard from '@features/posts/components/PostCard/PostCard';

const PostsList = () => {
  return (
    <section className="grid grid-cols-3 gap-x-16 gap-y-8">
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
    </section>
  );
};
export default PostsList;
