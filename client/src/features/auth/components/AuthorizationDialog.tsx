import { useSearchParams } from 'react-router-dom';
import LoginContent from './Login/LoginContent';
import SignupContent from './Signup/SignupContent';
import classNames from 'classnames';
const AuthorizationDialog = () => {
  const [searchParams] = useSearchParams();

  const isLogin = searchParams.get('mode') === 'login';

  return (
    <div className="relative mt-20 grid h-[590px] w-full max-w-[1200px] grid-cols-2 items-center overflow-hidden rounded-[40px] bg-gray-50">
      <div
        className={classNames(
          'absolute left-0 h-full w-1/2 bg-green-700 transition-all duration-500',
          {
            'translate-x-full': isLogin,
          },
        )}
      />
      {isLogin ? <LoginContent /> : <SignupContent />}
    </div>
  );
};
export default AuthorizationDialog;
