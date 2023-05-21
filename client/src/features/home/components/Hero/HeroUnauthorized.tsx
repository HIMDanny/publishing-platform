import Button from '@components/UI/Button';
import classNames from 'classnames';

const Hero = () => {
  return (
    <section
      className={classNames(
        'flex h-screen items-center bg-gray-800',
        `bg-[url(./hero-section-unauthorized-bg.png)] bg-cover bg-center bg-no-repeat`,
      )}
    >
      <div className="container mx-auto">
        <h2 className="text-8xl text-white">Find your topic!</h2>
        <p className="mt-9 mb-10 max-w-2xl text-2xl text-gray-50 text-opacity-70">
          Praesent id volutpat sapien, vitae sollicitudin lorem. Integer non
          augue at eros condimentum tincidunt!
        </p>
        <Button
          size="lg"
          text="Start reading"
        />
      </div>
    </section>
  );
};
export default Hero;
