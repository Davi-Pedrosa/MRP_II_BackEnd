package com.mrp2.backend.config;

import com.mrp2.backend.model.*;
import com.mrp2.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final UsuarioRepository usuarioRepository;
    private final EquipeRepository equipeRepository;
    private final MembroEquipeRepository membroEquipeRepository;
    private final EstoqueRepository estoqueRepository;
    private final FornecedorRepository fornecedorRepository;
    private final InspecaoQualidadeRepository inspecaoQualidadeRepository;
    private final SolicitacaoManutencaoRepository solicitacaoManutencaoRepository;
    private final FinanceiroRepository financeiroRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        try {
            logger.info("Iniciando cadastro de dados iniciais...");
            
            // Primeiro, vamos verificar se existem usuários
            long userCount = usuarioRepository.count();
            logger.info("Número de usuários no banco: {}", userCount);

            // Criar usuários
            createOrUpdateUser(
                "admin@admin.com",
                "Administrador",
                "admin123",
                "ADMIN"
            );

            createOrUpdateUser(
                "funcionario@empresa.com",
                "Funcionário",
                "func123",
                "FUNCIONARIO"
            );

            createOrUpdateUser(
                "manutencao@empresa.com",
                "Manutenção",
                "manut123",
                "MANUTENCAO"
            );

            // Verificar novamente o número de usuários
            long finalUserCount = usuarioRepository.count();
            logger.info("Número final de usuários no banco: {}", finalUserCount);

            // Listar todos os usuários para verificação
            logger.info("Lista de todos os usuários cadastrados:");
            usuarioRepository.findAll().forEach(user -> {
                logger.info("Usuário: {}, Papel: {}, Senha Hash: {}", 
                    user.getEmail(), 
                    user.getPapel(),
                    user.getSenha().substring(0, 10) + "..."
                );
            });

            // Criar equipe e membros
            if (equipeRepository.count() == 0) {
                logger.info("Criando equipe inicial...");
                Equipe equipe = new Equipe();
                equipe.setNome("Equipe de Produção");
                equipe.setTipo("Produção");
                equipe.setStatus(Equipe.Status.NORMAL);
                equipe.setTendencia(Equipe.Tendencia.ESTAVEL);
                equipe.setCapacidadeDiaria(100);
                equipe.setEmUso(0.0);
                equipe.setCpusEmProcessamento(0);
                equipe.setTempoPorUnidade(30);
                equipe = equipeRepository.save(equipe);
                logger.info("Equipe criada com sucesso: {}", equipe.getNome());

                MembroEquipe membro1 = new MembroEquipe();
                membro1.setNome("João Silva");
                membro1.setFuncao("Operador");
                membro1.setDisponivel(true);
                membro1.setEquipe(equipe);
                membro1.setHabilidades(Arrays.asList("Operação de Máquinas", "Manutenção Básica"));
                membroEquipeRepository.save(membro1);
                logger.info("Membro da equipe criado com sucesso: {}", membro1.getNome());
            }

            // Criar fornecedor
            if (fornecedorRepository.count() == 0) {
                logger.info("Criando fornecedor...");
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome("Fornecedor ABC");
                fornecedor.setEmail("contato@fornecedor.com");
                fornecedor.setTelefone("(11) 1234-5678");
                fornecedor.setEndereco("Rua das Indústrias, 123");
                fornecedor.setPessoaContato("Maria Silva");
                fornecedor = fornecedorRepository.save(fornecedor);
                logger.info("Fornecedor criado com sucesso: {}", fornecedor.getNome());

                // Criar itens de estoque
                logger.info("Criando itens de estoque...");
                Estoque item1 = new Estoque();
                item1.setNome("Matéria Prima A");
                item1.setCategoria("Matéria Prima");
                item1.setQuantidade(100);
                item1.setQuantidadeMinima(20);
                item1.setQuantidadeMaxima(200);
                item1.setUnidade("kg");
                item1.setLocalizacao("Galpão A");
                item1.setFornecedor(fornecedor);
                estoqueRepository.save(item1);
                logger.info("Item de estoque criado com sucesso: {}", item1.getNome());
            }

            // Criar inspeção de qualidade
            if (inspecaoQualidadeRepository.count() == 0) {
                logger.info("Criando inspeção de qualidade...");
                InspecaoQualidade inspecao = new InspecaoQualidade();
                inspecao.setDataInspecao(LocalDateTime.now());
                inspecao.setProdutoId("PROD-001");
                inspecao.setNumeroLote("LOTE-2024-001");
                inspecao.setStatus(InspecaoQualidade.Status.APROVADO);
                inspecao.setObservacoes("Produto dentro das especificações");
                inspecaoQualidadeRepository.save(inspecao);
                logger.info("Inspeção de qualidade criada com sucesso: {}", inspecao.getProdutoId());
            }

            // Criar solicitação de manutenção
            if (solicitacaoManutencaoRepository.count() == 0) {
                logger.info("Criando solicitação de manutenção...");
                SolicitacaoManutencao solicitacao = new SolicitacaoManutencao();
                solicitacao.setDataSolicitacao(LocalDateTime.now());
                solicitacao.setEquipamento("Máquina de Produção 01");
                solicitacao.setDescricao("Manutenção preventiva necessária");
                solicitacao.setPrioridade(SolicitacaoManutencao.Prioridade.MEDIA);
                solicitacao.setStatus(SolicitacaoManutencao.Status.PENDENTE);
                solicitacao.setDepartamento("Produção");
                solicitacaoManutencaoRepository.save(solicitacao);
                logger.info("Solicitação de manutenção criada com sucesso: {}", solicitacao.getEquipamento());
            }

            // Criar lançamento financeiro
            if (financeiroRepository.count() == 0) {
                logger.info("Criando lançamento financeiro...");
                Financeiro lancamento = new Financeiro();
                lancamento.setData(LocalDateTime.now());
                lancamento.setCustoMaoObra(new BigDecimal("5000.00"));
                lancamento.setCustoMateriais(new BigDecimal("10000.00"));
                lancamento.setCustoEquipamentos(new BigDecimal("2000.00"));
                lancamento.setCustoUtilidades(new BigDecimal("1000.00"));
                lancamento.setCustoManutencao(new BigDecimal("500.00"));
                lancamento.setCustoTotal(new BigDecimal("18500.00"));
                lancamento.setVendasTotais(new BigDecimal("30000.00"));
                lancamento.setPrecoMedioUnidade(new BigDecimal("100.00"));
                lancamento.setUnidadesProduzidas(300);
                lancamento.setLucroBruto(new BigDecimal("11500.00"));
                lancamento.setLucroLiquido(new BigDecimal("8050.00"));
                lancamento.setMargemLucro(new BigDecimal("0.27"));
                lancamento.setDepartamento("Produção");
                lancamento.setLinhaProduto("Linha A");
                financeiroRepository.save(lancamento);
                logger.info("Lançamento financeiro criado com sucesso: {}", lancamento.getData());
            }

            logger.info("Inicialização de dados concluída com sucesso!");
            
        } catch (Exception e) {
            logger.error("Erro durante a inicialização dos dados: ", e);
            throw e;
        }
    }

    private void createOrUpdateUser(String email, String nome, String senha, String papel) {
        try {
            Optional<Usuario> existingUser = usuarioRepository.findByEmail(email);
            
            if (existingUser.isEmpty()) {
                logger.info("Criando novo usuário: {}", email);
                
                String hashedPassword = passwordEncoder.encode(senha);
                logger.info("Hash gerado para {}: {}", email, hashedPassword);
                
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(hashedPassword);
                usuario.setPapel(papel);
                
                usuario = usuarioRepository.save(usuario);
                logger.info("Usuário criado com sucesso: {} com papel {}", email, papel);
            } else {
                logger.info("Atualizando usuário existente: {}", email);
                
                Usuario usuario = existingUser.get();
                String hashedPassword = passwordEncoder.encode(senha);
                logger.info("Novo hash gerado para {}: {}", email, hashedPassword);
                
                usuario.setNome(nome);
                usuario.setSenha(hashedPassword);
                usuario.setPapel(papel);
                
                usuarioRepository.save(usuario);
                logger.info("Usuário atualizado com sucesso: {}", email);
            }
        } catch (Exception e) {
            logger.error("Erro ao criar/atualizar usuário {}: {}", email, e.getMessage());
            throw e;
        }
    }
} 