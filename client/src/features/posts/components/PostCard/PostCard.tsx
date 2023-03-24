import PostCardInfo from './PostCardInfo';

const PostCard = () => {
  return (
    <article className="max-w-[33.125rem]">
      <div className="relative">
        <img
          src="https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg"
          alt=""
          className="block h-[17.5rem] w-full rounded-[40px_40px_0_40px] object-cover"
        />
        <div className="absolute right-0 -bottom-2 rounded-[6px_2px_5px_6px] bg-white p-[0.375rem] shadow-[2px_1px_4px_rgba(0,0,0,0.25)]">
          <span className="text-sm tracking-wide text-gray-700">
            Conversation Design
          </span>
        </div>
      </div>
      <h6 className="mt-6">
        Good Bot Design Means Never Having to Say: ‘I’m sorry, I didn’t get
        that’
      </h6>
      <PostCardInfo />
    </article>
  );
};
export default PostCard;
