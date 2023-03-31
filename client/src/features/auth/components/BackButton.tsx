import { Link } from 'react-router-dom';

const BackButton = () => (
  <Link
    to=".."
    className="group flex w-fit items-center gap-2"
  >
    <svg
      className="transition-transform group-hover:-translate-x-[2px]"
      xmlns="http://www.w3.org/2000/svg"
      width="12"
      height="18"
      viewBox="0 0 12 18"
      fill="none"
    >
      <path
        d="M10.4942 0.192356L0.825488 8.53064C0.54873 8.76931 0.54873 9.22888 0.825488 9.47009L10.4942 17.8084C10.8548 18.1181 11.3829 17.8388 11.3829 17.3386V0.662083C11.3829 0.161887 10.8548 -0.117409 10.4942 0.192356Z"
        fill="black"
        fillOpacity="0.85"
      />
    </svg>
    Back
  </Link>
);

export default BackButton;
