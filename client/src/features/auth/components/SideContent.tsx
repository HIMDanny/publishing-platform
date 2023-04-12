import Button from '@components/UI/Button';
import { Link } from 'react-router-dom';

export type SideContentProps = {
  title: string;
  body: string;
  buttonText: string;
  navigateTo: string;
};

const SideContent: React.FC<SideContentProps> = ({
  title,
  body,
  buttonText,
  navigateTo,
}) => {
  return (
    <div className="isolate px-16 pt-20 pb-8 text-center text-gray-100">
      <h3>{title}</h3>
      <p className="mt-9 mb-10 text-gray-100 text-opacity-70">{body}</p>
      <Button
        size="lg"
        text={buttonText}
        component={Link}
        to={navigateTo}
      />
    </div>
  );
};
export default SideContent;
