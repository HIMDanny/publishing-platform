import Button from '@components/UI/Button';

export type SideContentProps = {
  title: string;
  body: string;
  buttonText: string;
};

const SideContent: React.FC<SideContentProps> = ({
  title,
  body,
  buttonText,
}) => {
  return (
    <div className="isolate space-y-10 px-16 pt-20 pb-8 text-center text-gray-50">
      <h3>{title}</h3>
      <p>{body}</p>
      <Button size="lg">{buttonText}</Button>
    </div>
  );
};
export default SideContent;
