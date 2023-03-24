import CardsCarousel from './CardsCarousel';

export type PostCardSectionProps = {
  title: string;
};

const PostCardSection = ({ title }: PostCardSectionProps) => {
  return (
    <section>
      <h4 className="mb-2">{title}</h4>
      <CardsCarousel />
    </section>
  );
};
export default PostCardSection;
