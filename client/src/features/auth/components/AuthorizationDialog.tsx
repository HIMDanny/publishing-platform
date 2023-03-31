import LoginContent from './Login/LoginContent';

const AuthorizationDialog = () => {
  return (
    <div className="relative mt-20 grid w-full max-w-[1200px] grid-cols-2 items-center overflow-hidden rounded-[40px] bg-gray-50">
      <div className="absolute right-0 h-full w-1/2 bg-green-700" />
      <LoginContent />
    </div>
  );
};
export default AuthorizationDialog;
