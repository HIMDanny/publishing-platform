import { useEffect, useRef } from 'react';

export type SliderPaginationProps = {
  dots: React.ReactNode;
  className?: string;
  style?: React.CSSProperties;
};

const isRightOffset = (
  activeIndex: number,
  pageIndex: number,
  offset: number,
) => pageIndex + offset === activeIndex || pageIndex === activeIndex + offset;

const SliderPagination = ({
  dots,
  className,
  style,
}: SliderPaginationProps) => {
  const listRef = useRef<HTMLUListElement>(null);

  useEffect(() => {
    const children = Array.from(
      listRef.current?.children as ArrayLike<Element>,
    );

    const activePageIndex = children.findIndex((page) =>
      page.classList.contains('slick-active'),
    );

    children.forEach((page, index) => {
      page.classList.remove('dot-lg', 'dot-md', 'dot-sm');
      if (isRightOffset(activePageIndex, index, 1)) {
        page.classList.add('dot-lg');
      }

      if (isRightOffset(activePageIndex, index, 2)) {
        page.classList.add('dot-md');
      }

      if (isRightOffset(activePageIndex, index, 3)) {
        page.classList.add('dot-sm');
      }
    });
  }, [dots]);

  return (
    <ul
      ref={listRef}
      className={className}
      style={style}
    >
      {dots}
    </ul>
  );
};
export default SliderPagination;
