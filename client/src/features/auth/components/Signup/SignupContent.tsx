import { animation } from '@features/auth/utils/auth-content-animation';
import BackButton from '../BackButton';
import SideContent from '../SideContent';
import SignupForm from './SignupForm';
import { motion } from 'framer-motion';

const SignupContent = () => {
  return (
    <>
      <SideContent
        title="Hello, Friends!"
        body="To keep connected whith us plesase login with your personal info"
        buttonText="Sign In"
        navigateTo="?mode=login"
      />
      <motion.div
        initial={{ opacity: 0 }}
        animate={animation}
        className="px-16 pt-20 pb-8"
      >
        <SignupForm />
        <div className="mt-9 flex justify-end">
          <BackButton />
        </div>
      </motion.div>
    </>
  );
};
export default SignupContent;
