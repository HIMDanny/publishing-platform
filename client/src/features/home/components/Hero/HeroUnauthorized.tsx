import Button from '@components/UI/Button';
import classNames from 'classnames';

const Hero = () => {
  return (
    <section
      className={classNames(
        'flow-root h-screen bg-gray-800',
        `bg-[url(./hero-section-unauthorized-bg.png)] bg-center bg-no-repeat`,
      )}
    >
      <div className="container mx-auto mt-72">
        <h2 className="text-8xl text-white">Find your topic!</h2>
        <p className="mt-9 mb-10 max-w-2xl text-2xl text-gray-50 text-opacity-70">
          Praesent id volutpat sapien, vitae sollicitudin lorem. Integer non
          augue at eros condimentum tincidunt!
        </p>
        <Button size="lg">Start reading</Button>
      </div>
    </section>
  );
};
export default Hero;
