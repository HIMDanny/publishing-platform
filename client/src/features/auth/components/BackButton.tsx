import { Link } from 'react-router-dom';

const BackButton = () => (
  <Link
    to=".."
    className="group flex w-fit items-center gap-1"
  >
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="18"
      height="18"
      viewBox="0 0 18 18"
      fill="none"
      className="transition-transform group-hover:-translate-x-[2px]"
    >
      <path
        d="M12.1114 2.9024L5.41763 8.67506C5.22603 8.84029 5.22603 9.15845 5.41763 9.32545L12.1114 15.0981C12.361 15.3126 12.7266 15.1192 12.7266 14.7729V3.2276C12.7266 2.88131 12.361 2.68795 12.1114 2.9024Z"
        fill="black"
        fillOpacity="0.85"
      />
    </svg>
    Back
  </Link>
);

export default BackButton;
