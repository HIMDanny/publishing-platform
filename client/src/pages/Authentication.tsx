import Logo from '@components/UI/Logo';
import { AuthorizationDialog } from '@features/auth';

// TODO: change logo to dark variant

const Authentication = () => {
  return (
    <>
      <header className="container mx-auto py-6">
        <Logo />
      </header>
      <main className="flex items-center justify-center text-black">
        <AuthorizationDialog />
      </main>
    </>
  );
};
export default Authentication;
