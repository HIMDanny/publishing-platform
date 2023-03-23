import Button from '@components/UI/Button';

const HighlightedPost = () => {
  return (
    <article className="flex gap-10">
      <div className="py-24">
        <h3 className="mb-6">
          The most powerful woman you&apos;ve never heard of
        </h3>
        <p className="mb-11">
          Everyone&apos;s heard of Martin Luther King Jr. But do you know the
          woman Dr. King called &quot;the architect of the civil rights
          movement,&quot; Septima Clark? The teacher of some of the
          generation&apos;s most legendary activists -- like Rosa Parks, Diane
          Nash, Fannie Lou Hamer and thousands more...
        </p>
        <Button
          variant="secondary"
          size="lg"
        >
          Read more
        </Button>
      </div>
      <img
        className="block max-h-[27.5rem] w-full max-w-xl overflow-hidden rounded-lg bg-gray-200 object-cover"
        src="https://imglarger.com/Images/before-after/ai-image-enlarger-1-after-2.jpg"
        alt=""
      />
    </article>
  );
};
export default HighlightedPost;
