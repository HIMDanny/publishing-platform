import LoginForm from './LoginForm';
import BackButton from '../BackButton';
import SideContent from '../SideContent';
import { motion } from 'framer-motion';
import { animation } from '@features/auth/utils/auth-content-animation';

const LoginContent = () => {
  return (
    <>
      <motion.div
        initial={{ opacity: 0 }}
        animate={animation}
        className="px-16 pt-20 pb-8"
      >
        <LoginForm />
        <div className="mt-9">
          <BackButton />
        </div>
      </motion.div>
      <SideContent
        title="Welcome Back!"
        body="In case you are not signed up, click on the button below to start your
          journey with us"
        buttonText="Sign Up"
        navigateTo="?mode=signup"
      />
    </>
  );
};
export default LoginContent;
