import { Outlet } from 'react-router-dom';
import Header from 'layout/Header/Header';
import Footer from 'layout/Footer/Footer';

const Root = () => {
  return (
    <>
      <Header />
      <main>
        <Outlet />
      </main>
      <Footer />
    </>
  );
};
export default Root;
