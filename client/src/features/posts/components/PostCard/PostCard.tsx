import { Link } from 'react-router-dom';
import BookmarkButton from './BookmarkButton';
import PostCardInfo from './PostCardInfo';

const PostCard = () => {
  const handleBookmarkToggle = () => {
    console.log('bookmarked');
  };

  return (
    <article className="max-w-[33.125rem]">
      <div className="group relative">
        <Link
          to="/test"
          className="relative"
        >
          <img
            src="https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg"
            alt=""
            className="block h-[17.5rem] w-full rounded-[40px_40px_0_40px] object-cover"
          />
        </Link>
        <BookmarkButton
          isBookmarked={false}
          onBookmarkToggle={handleBookmarkToggle}
        />
        <div className="absolute right-0 -bottom-2 rounded-[6px_2px_5px_6px] bg-white p-[0.375rem] shadow-[2px_1px_4px_rgba(0,0,0,0.25)]">
          {/* TODO: add hover effect on link */}
          <Link
            to="/"
            className="text-sm tracking-wide text-gray-700"
          >
            Conversation Design
          </Link>
        </div>
      </div>
      {/* added temporary hover effect. Possibly would change in future */}
      <h6 className="mt-6 hover:underline">
        <Link to="/">
          Good Bot Design Means Never Having to Say: ‘I’m sorry, I didn’t get
          that’
        </Link>
      </h6>
      <PostCardInfo />
    </article>
  );
};
export default PostCard;
