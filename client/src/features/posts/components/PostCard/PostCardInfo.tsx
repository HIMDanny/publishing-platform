const PostCardInfo = () => {
  return (
    <div className="mt-2 flex items-center justify-between text-sm tracking-wide">
      <div className="flex items-center gap-2">
        <img
          src="https://www.w3schools.com/howto/img_avatar.png"
          alt=""
          className="h-10 w-10 rounded-full"
        />
        <span>Jone Yero</span>
      </div>
      <div className="text-gray-700">
        <span>Jan 31</span> &bull; <span>7 min read</span>
      </div>
    </div>
  );
};
export default PostCardInfo;
