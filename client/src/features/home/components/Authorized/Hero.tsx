import classNames from 'classnames';

const Hero = () => {
  return (
    <section
      className={classNames(
        'pb-36 pt-60 text-white',
        'bg-[url(./small-bg-with-lights.png)] bg-cover bg-center bg-no-repeat',
      )}
    >
      <h2 className="heading-1 text-center">Find your topic!</h2>
    </section>
  );
};
export default Hero;
