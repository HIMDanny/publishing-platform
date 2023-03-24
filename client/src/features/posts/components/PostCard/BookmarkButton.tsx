export type BookmarkButtonProps = {
  isBookmarked: boolean;
  onBookmarkToggle: React.MouseEventHandler<HTMLButtonElement>;
};

const BookmarkButton = ({
  isBookmarked,
  onBookmarkToggle,
}: BookmarkButtonProps) => {
  return (
    <button
      type="button"
      className="invisible absolute top-3 right-3 rounded-full bg-gray-50 bg-opacity-80 p-3 hover:bg-gray-100 active:bg-gray-200 group-hover:visible"
      onClick={onBookmarkToggle}
    >
      <span className="sr-only">Bookmark post</span>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill={isBookmarked ? 'black' : 'none'}
        viewBox="0 0 24 24"
        strokeWidth={1.5}
        stroke="currentColor"
        className="h-6 w-6"
      >
        <path
          strokeLinecap="round"
          strokeLinejoin="round"
          d="M17.593 3.322c1.1.128 1.907 1.077 1.907 2.185V21L12 17.25 4.5 21V5.507c0-1.108.806-2.057 1.907-2.185a48.507 48.507 0 0111.186 0z"
        />
      </svg>
    </button>
  );
};
export default BookmarkButton;
