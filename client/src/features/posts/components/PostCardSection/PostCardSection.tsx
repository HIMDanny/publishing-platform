import CardsCarousel from './CardsCarousel/CardsCarousel';

export type PostCardSectionProps = {
  title: string;
};

const PostCardSection = ({ title }: PostCardSectionProps) => {
  return (
    <section className="mb-9">
      <h4 className="mb-2">{title}</h4>
      <CardsCarousel />
    </section>
  );
};
export default PostCardSection;
