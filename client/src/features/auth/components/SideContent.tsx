import Button from '@components/UI/Button';
import { Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { animation } from '@features/auth/utils/auth-content-animation';

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
    <motion.div
      initial={{ opacity: 0 }}
      animate={animation}
      className="isolate px-16 pt-20 pb-8 text-center text-gray-100"
    >
      <h3>{title}</h3>
      <p className="mt-9 mb-10 text-gray-100 text-opacity-70">{body}</p>
      <Button
        size="lg"
        text={buttonText}
        component={Link}
        to={navigateTo}
      />
    </motion.div>
  );
};
export default SideContent;
